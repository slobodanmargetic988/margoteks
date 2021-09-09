/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.controller;

import com.margotekstil.configuration.MargotekstilUserPrincipal;
import com.margotekstil.model.Korpa;
import com.margotekstil.model.KorpaProizvodi;
import com.margotekstil.model.Proizvodi;
import com.margotekstil.model.Users;
import com.margotekstil.model.ZavrsenePorudzbine;
import com.margotekstil.service.ColorPaletaService;
import com.margotekstil.service.KorpaProizvodiService;
import com.margotekstil.service.KorpaService;
import com.margotekstil.service.ProizvodiService;
import org.springframework.beans.factory.annotation.Autowired;

import com.margotekstil.service.UsersService;
import com.margotekstil.service.ZavrsenePorudzbineService;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

/**
 *
 * @author Aleksandra
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@RestController
public class KorpaRestController {

    @Autowired
    private ZavrsenePorudzbineService zavrsenePorudzbineService;
    
 @Autowired
    private ColorPaletaService colorPaletaService;
 
    @GetMapping("/korpa/zavrsiPorudzbinu/neregistrovan")
    String zavrsiPorudzbinuMargotekstilNeregistrovan(
            @RequestParam("myData") String myData
    ) {
        // System.out.println(myData);
        ZavrsenePorudzbine zavrsena = new ZavrsenePorudzbine();
        Users user = new Users();
        Korpa tempKorpa = new Korpa();
        List<KorpaProizvodi> templista = new ArrayList();
        try {
            JSONObject dataJSON = new JSONObject(myData);
            //System.out.println(dataJSON);
            JSONObject korisnik = dataJSON.getJSONObject("korisnik");

            JSONArray korpa = dataJSON.getJSONArray("korpa");

            user.setIme(korisnik.getString("ime"));
            user.setPrezime(korisnik.getString("prezime"));
            user.setBroj_telefona(korisnik.getString("telefon"));
            user.setEmail(korisnik.getString("email"));
            user.setAdresa(korisnik.getString("adresa"));
            user.setPostanski_broj(korisnik.getString("postanskibroj"));
            user.setMesto(korisnik.getString("grad"));
            userService.saveAndFlush(user);

            korpaService.save(tempKorpa);

            for (int i = 0; i < korpa.length(); i++) {
                JSONObject temp = korpa.getJSONObject(i);
                KorpaProizvodi tempKP = new KorpaProizvodi();
                tempKP.setKorpa(tempKorpa);
                tempKP.setProizvod(proizvodiService.findFirstById(parseInt(temp.getString("idProizvoda"))));
                tempKP.setKolicina(temp.getInt("kolicina"));
                int boja=temp.getInt("boja");
                if (boja!=0){
                tempKP.setBoja(colorPaletaService.findFirstById(boja));
                }
                korpaProizvodiService.saveAndFlush(tempKP);
                templista.add(tempKP);

            }
            // System.out.println("izaso iz petlje");
            tempKorpa.setKorpaproizvodi(templista);
            korpaService.saveAndFlush(tempKorpa);
            //tempKorpa.setKorpaproizvodi(templista);
            zavrsena.setUser(user);
            zavrsena.setKorpa(tempKorpa);

            zavrsena.setAdresa(korisnik.getString("adresa"));
            zavrsena.setBroj_telefona(parseInt(korisnik.getString("telefon")));
            zavrsena.setEmail(korisnik.getString("email"));
            zavrsena.setGrad(korisnik.getString("grad"));
            zavrsena.setIme(korisnik.getString("ime"));
            zavrsena.setNacin_placanja(korisnik.getString("nacinplacanja"));
            zavrsena.setNapomena(korisnik.getString("napomena"));
            zavrsena.setPostanski_broj(korisnik.getString("postanskibroj"));
            zavrsena.setPrezime(korisnik.getString("prezime"));
           
            zavrsenePorudzbineService.saveAndFlush(zavrsena);
        } catch (Exception e) {

            System.out.println(e);
return e.getMessage();
        }
        try {
            if (zavrsena.getNacin_placanja().equalsIgnoreCase("Plaćanje prilikom preuzimanja")) {
                //  System.out.println("so far so good2");
                String htmlPrikaz = "";
                EmailController.SendVasaPorudzbinaEmail(user, zavrsena);

                EmailController.SendkorisnikPorucioEmail(user, zavrsena);

            } else {
                // System.out.println("so far so good3");
                if (zavrsena.getNacin_placanja().equalsIgnoreCase("Uplata na tekući račun")) {
                    // System.out.println("so far so good4");
                    String htmlPrikaz = "";
                    String uplatnica = "";
                    EmailController.SendVasaPorudzbinaiUplatnicaEmail( user, zavrsena);
                    //  System.out.println("so far so good5");
                    EmailController.SendkorisnikPorucioEmail(user, zavrsena);
//System.out.println("so far so good6");
                } else {
                    String htmlPrikaz = "";
                    EmailController.SendVasaPorudzbinaEmail(user, zavrsena);

                    EmailController.SendkorisnikPorucioEmail(user, zavrsena);

                }

                user.setEmail("email");
                userService.save(user);
            }
        } catch (Exception e) {

            System.out.println(e);
             return e.getMessage();
        }

        return "ok";
   
    }

    @Autowired
    private UsersService userService;

    @Autowired
    private KorpaProizvodiService korpaProizvodiService;

    @Autowired
    private KorpaService korpaService;

    @Autowired
    private ProizvodiService proizvodiService;

    @GetMapping("/korpa/dodajProizvod/{proizvod_id}")
    String dodajProizvodG(@PathVariable final int proizvod_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
        Korpa korpa = myUser.getKorpa();
        Proizvodi proizvod = proizvodiService.findFirstById(proizvod_id);
        KorpaProizvodi postojeciProizvod = korpaProizvodiService.findFirstByKorpaAndProizvod(korpa, proizvod);

        KorpaProizvodi novProizvod = new KorpaProizvodi();
        novProizvod.setKorpa(korpa);
        novProizvod.setProizvod(proizvod);
        novProizvod.setKolicina(1);

        try {
            if (postojeciProizvod == null) {
                korpaProizvodiService.save(novProizvod);
                
            } else {
                //ne radimo nista vec je u korpi
                return "Proizvod je već u korpi!";
            }
            // korpa.getKorpaproizvodi().add(novProizvod);
            //  korpaService.save(korpa);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("ovde");
            
            return "Proizvod nije dodat u korpu!";
        }
        return "Proizvod je uspešno dodat u korpu!";
    }

   
       
    @GetMapping("/korpa/dodajProizvodQty/{proizvod_id}/{kolicina}/{boja}")
    String dodajProizvodQty(@PathVariable final int proizvod_id,
            @PathVariable final int kolicina,
            @PathVariable final int boja
    ) {
        if (kolicina < 0) {
            return "Morate uneti količinu veću od nula!";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
        Korpa korpa = myUser.getKorpa();
        Proizvodi proizvod = proizvodiService.findFirstById(proizvod_id);
        KorpaProizvodi postojeciProizvod = korpaProizvodiService.findFirstByKorpaAndProizvodAndBoja(korpa, proizvod,colorPaletaService.findFirstById(boja));

        KorpaProizvodi novProizvod = new KorpaProizvodi();
        novProizvod.setKorpa(korpa);
        novProizvod.setProizvod(proizvod);
        novProizvod.setKolicina(kolicina);
       // novProizvod.setBoja(colorPaletaService.findFirstById(boja));
        
if(boja>0){//default boja je 0 pa ne dodajemo ako je defaultna
        novProizvod.setBoja(colorPaletaService.findFirstById(boja));
}
        
        try {
            if (postojeciProizvod == null) {
                korpaProizvodiService.save(novProizvod);
            } else {
            
                if (postojeciProizvod.getKolicina() != kolicina) {
                    postojeciProizvod.setKolicina(kolicina);
                    korpaProizvodiService.save(postojeciProizvod);
                    return "Proizvod je već u korpi, promenjena je količina !";
                }
                  return "Proizvod je već u korpi!";
                
                //ne radimo nista vec je u korpi
              
            }
            // korpa.getKorpaproizvodi().add(novProizvod);
            //  korpaService.save(korpa);
        } catch (Exception e) {
            return "Proizvod nije dodat u korpu!";
        }
        return "Proizvod je uspešno dodat u korpu!";
    }

    @GetMapping("/korpa/promeniKolicinu/{kproizvod_id}/{kolicina}/")
    String promeniKolicinu(@PathVariable final int kproizvod_id,
            @PathVariable final int kolicina
    ) {
        if (kolicina < 0) {
            return "Morate uneti količinu veću od nula!";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
        Korpa korpa = myUser.getKorpa();
    //    Proizvodi proizvod = proizvodiService.findFirstById(proizvod_id);
        KorpaProizvodi postojeciProizvod = korpaProizvodiService.findOne(kproizvod_id);
        try {
            postojeciProizvod.setKolicina(kolicina);
            korpaProizvodiService.save(postojeciProizvod);
        } catch (Exception e) {
            return "Količina nije izmenjena!";
        }
        return "Kolicina je uspešno izmenjena!";
    }

    @GetMapping("/korpa/povecajKolicinu/{kproizvod_id}")
    String povecajKolicinu(@PathVariable final int kproizvod_id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
        Korpa korpa = myUser.getKorpa();
     //   Proizvodi proizvod = proizvodiService.findFirstById(proizvod_id);
        KorpaProizvodi postojeciProizvod = korpaProizvodiService.findOne(kproizvod_id);
        try {
            postojeciProizvod.setKolicina(postojeciProizvod.getKolicina() + 1);

            korpaProizvodiService.save(postojeciProizvod);
        } catch (Exception e) {
            return "Kolicina nije uvecana!";
        }
        return "Uspešno je uvećana količina!";
    }

    @GetMapping("/korpa/smanjiKolicinu/{kproizvod_id}")
    String smanjiKolicinu(@PathVariable final int kproizvod_id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
        Korpa korpa = myUser.getKorpa();
      //  Proizvodi proizvod = proizvodiService.findFirstById(proizvod_id);
    KorpaProizvodi postojeciProizvod = korpaProizvodiService.findOne(kproizvod_id);
    try {

            if (postojeciProizvod.getKolicina() > 0) {
                postojeciProizvod.setKolicina(postojeciProizvod.getKolicina() - 1);
                korpaProizvodiService.save(postojeciProizvod);
            } else {
                System.out.println("Kolicina je nula!");
                return "Kolicina je nula!";
            }

        } catch (Exception e) {
             System.out.println("returnolicina nije smanjena!");
            return "returnolicina nije smanjena!";
        }
       System.out.println("Kolicina je uspešno smanjena!!");
        return "Kolicina je uspešno smanjena!";
    }

    @GetMapping("/korpa/skloniProizvod/{kproizvod_id}")
    String skloniProizvod(@PathVariable final int kproizvod_id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
        Korpa korpa = myUser.getKorpa();
      //  Proizvodi proizvod = proizvodiService.findFirstById(proizvod_id);
    KorpaProizvodi postojeciProizvod = korpaProizvodiService.findOne(kproizvod_id);
    try {
            korpaProizvodiService.delete(postojeciProizvod);
        } catch (Exception e) {
            System.out.println(e);
            return "Proizvod nije uklonjen iz korpe!";
        }
        return "Proizvod je uspešno uklonjen iz korpe!";
    }

    private Integer Integer(String quantity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
