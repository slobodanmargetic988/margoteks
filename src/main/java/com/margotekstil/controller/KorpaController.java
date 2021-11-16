/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.controller;

import com.margotekstil.configuration.MargotekstilUserPrincipal;
import com.margotekstil.model.Korpa;
import com.margotekstil.model.KorpaProizvodi;
import com.margotekstil.model.Photo;
import com.margotekstil.model.Proizvodi;
import com.margotekstil.model.ResetTokeni;
import com.margotekstil.model.Users;
import com.margotekstil.model.ZavrsenePorudzbine;
import com.margotekstil.repository.UsersRepository;
import com.margotekstil.service.KorpaService;
import com.margotekstil.service.PhotoService;
import com.margotekstil.service.ProizvodiService;
import com.margotekstil.service.ResetTokeniService;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.margotekstil.service.UsersService;
import com.margotekstil.service.ZavrsenePorudzbineService;
import com.margotekstil.storage.StorageService;
import java.util.List;
import java.util.Random;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Aleksandra
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
public class KorpaController {

    @GetMapping(value = "/korpa")
    public String publicShoppingCart2Margotekstil(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
            Users user = userService.findFirstByEmail(myUser.getEmail());
            model.addAttribute("user", user);
            List<KorpaProizvodi> korpaproizvodi = user.getKorpa().getKorpaproizvodi();
            model.addAttribute("korpaproizvodi", korpaproizvodi);
            model.addAttribute("korpa", user.getKorpa());

        } else {
            return "neregistrovani/korpa";
        }

        return "main/korpa";
    }

    @Autowired
    private UsersService userService;
    @Autowired
    private KorpaService korpaService;
    @Autowired
    private ZavrsenePorudzbineService zavrsenePorudzbineService;

    @PostMapping(value = "/korpa/zavrsiPorudzbinu")
    public String zavrsiPorudzbinuMargotekstil(final Model model,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "ime") String ime,
            @RequestParam(name = "prezime") String prezime,
            @RequestParam(name = "telefon") Integer telefon,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "adresa") String adresa,
            @RequestParam(name = "postanskibroj") String postanskibroj,
            @RequestParam(name = "grad") String grad,
            @RequestParam(name = "komentar") String komentar,
            @RequestParam(name = "nacinplacanja") String nacinplacanja
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
            Users user = userService.findFirstByEmail(myUser.getEmail());
            ZavrsenePorudzbine zavrsena = new ZavrsenePorudzbine();

            zavrsena.setUser(user);
            zavrsena.setKorpa(user.getKorpa());

            zavrsena.setAdresa(adresa);
            zavrsena.setBroj_telefona(telefon);
            zavrsena.setEmail(email);
            zavrsena.setGrad(grad);
            zavrsena.setIme(ime);
            zavrsena.setNacin_placanja(nacinplacanja);
            zavrsena.setNapomena(komentar);
            zavrsena.setPostanski_broj(postanskibroj);
            zavrsena.setPrezime(prezime);
            EmailController em = new EmailController();
            try {
                zavrsenePorudzbineService.save(zavrsena);
                Korpa korpa = new Korpa();
                korpaService.save(korpa);
                user.setKorpa(korpa);
                userService.save(user);

                if (nacinplacanja.equalsIgnoreCase("Plaćanje prilikom preuzimanja")) {
                    String htmlPrikaz = "";
                    EmailController.SendVasaPorudzbinaEmail(user, zavrsena);

                    EmailController.SendkorisnikPorucioEmail(user, zavrsena);

                } else {
                    if (nacinplacanja.equalsIgnoreCase("Uplata na tekući račun")) {
                        String htmlPrikaz = "";
                        String uplatnica = "";
                        EmailController.SendVasaPorudzbinaiUplatnicaEmail(user, zavrsena);

                        EmailController.SendkorisnikPorucioEmail(user, zavrsena);

                    } else {
                        String htmlPrikaz = "";
                        EmailController.SendVasaPorudzbinaEmail(user, zavrsena);

                        EmailController.SendkorisnikPorucioEmail(user, zavrsena);

                    }
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            model.addAttribute("user", user);
            redirectAttributes.addFlashAttribute("successMessage", "Porudzbina je uspesno poslata. Stici ce vam email sa potvrdom.");
        }

        return "redirect:/";
    }

}
