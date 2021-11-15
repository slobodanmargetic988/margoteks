/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.controller;

import com.margotekstil.configuration.MargotekstilUserPrincipal;
import com.margotekstil.model.ColorPaleta;
import com.margotekstil.model.Korpa;
import com.margotekstil.model.KorpaProizvodi;
import com.margotekstil.model.Photo;
import com.margotekstil.model.Proizvodi;
import com.margotekstil.model.ResetTokeni;
import com.margotekstil.model.Users;
import com.margotekstil.model.ZavrsenePorudzbine;
import com.margotekstil.repository.UsersRepository;
import com.margotekstil.service.ColorPaletaService;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
public class MainController {

    @GetMapping("/loginTry")
    public String login(final Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return "main/registracija";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
            model.addAttribute("user", myUser);
        }

        return "main/home";
    }

    @GetMapping("/uspesanlogin")
    public String homePageLogin(Model model,
            RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
          
        Users user = userService.findFirstByEmail(myUser.getEmail());
          model.addAttribute("userLogedIn", user);
        }

        redirectAttributes.addFlashAttribute("successMessageLogin", "Korisnik je uspesno prijavljen.");

        return "redirect:/";
    }
    
    @GetMapping("/profil")
    public String profil(Model model,
            RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
          
        Users user = userService.findFirstByEmail(myUser.getEmail());
          model.addAttribute("userLogedIn", user);
        }

        

        return "/main/profil";
    }
    
       
    
     @RequestMapping(value = "/profil/save", method = RequestMethod.POST)
    public String profilSave(final Model model, final HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "prezime", required=false) String prezime,
            @RequestParam(name = "ime", required=false) String ime,
            @RequestParam(name = "email", required=false) String email,
            @RequestParam(name = "password", required=false) String lozinka,
            @RequestParam(name = "lozinkaRepeat", required=false) String lozinkaRepeat,
            @RequestParam(name = "adresa", required=false) String adresa,
            @RequestParam(name = "mesto", required=false) String mesto,
            @RequestParam(name = "postanskibroj", required=false) String postanskibroj,
            @RequestParam(name = "telefon", required=false) String telefon
    ) {
 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      
            Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
          
        Users user = userService.findFirstByEmail(myUser.getEmail());
       
        user.setIme(ime);
        user.setPrezime(prezime);
        user.setEmail(email);
       
        user.setAdresa(adresa);
        user.setPostanski_broj(postanskibroj);
        user.setMesto(mesto);
        user.setBroj_telefona(telefon);
      
if (!lozinkaRepeat.equals("")){
        if (lozinka.equals(lozinkaRepeat)) {
            if (lozinka.length() >= 8) {
                user.setPassword(bCryptPasswordEncoder.encode(lozinka));
         
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Lozinka mora imati najmanje 8 karaktera");

                return "redirect:/profil";
            }

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Ponovljena lozinka nije ista kao lozinka");

            return "redirect:/profil";
        }
}
               try {
                 
                    userService.save(user);
                  
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

                    return "redirect:/profil";
                }
        redirectAttributes.addFlashAttribute("successMessage", "Profil je uspesno izmenjen.");

        return "redirect:/profil";
    }
    
    
    
    
    
    @GetMapping(value = "/prodavnica")
    public String publicShopMargotekstil(final Model model, @PageableDefault(value = 12) final Pageable pageable) {

        model.addAttribute("listaProizvoda", proizvodiService.findAllByActiveOrderByImeAsc(true, pageable));
        model.addAttribute("listakategorija", proizvodiService.findListaKategorija());
         model.addAttribute("trenutnaKategorija", "sveKategorije");
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
                   return "neregistrovani/prodavnica";

            
        }
        
        return "main/prodavnica";
    }

    @GetMapping(value = "/prodavnica/{kategorija}")
    public String shopKategorijaMargotekstil(final Model model, @PageableDefault(value = 12) final Pageable pageable,
            @PathVariable final String kategorija
    ) {
        model.addAttribute("listaProizvoda", proizvodiService.findByKategorijaOrderByActiveDescImeAsc(kategorija, pageable));
        model.addAttribute("listakategorija", proizvodiService.findListaKategorija());
         model.addAttribute("trenutnaKategorija", kategorija);
         
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
                   return "neregistrovani/prodavnica";
        }
        return "main/prodavnica";
    }

    @GetMapping("/test")
    public String testPage(Model model) {
        return "main/test";
    }

    @GetMapping("/usloviKoriscenja")
    public String usloviKoriscenja(Model model) {
        return "main/usloviKoriscenja";
    }

    @GetMapping("/infoDostava")
    public String infoDostava(Model model) {
        return "main/infoDostava";
    }

    @GetMapping(value = "/kontakt")
    public String publicContactMargotekstil(final Model model) {

        return "main/kontakt";
    }

    @GetMapping(value = "/onama")
    public String publicOnamaMargotekstil(final Model model) {

        return "main/onama";
    }

    @GetMapping(value = "/galerija")
    public String publicGalerijaMargotekstil(final Model model, @PageableDefault(value = 12) final Pageable pageable) {

        model.addAttribute("listaSlika", photoService.findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(true,pageable));

        return "main/galerija";
    }

    @GetMapping(value = "/photo/{photoId}")
    public ResponseEntity<Resource> servePhoto(@PathVariable(name = "photoId") final Integer photoId) {

        Photo photo = photoService.findFirstById(photoId);
        String filename = photo.getFilename();
        Resource file = storageService.loadAsResource(0, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
    
    @Autowired
    ColorPaletaService colorPaletaService;
        
    @GetMapping(value = "/boja/{bojaId}")
    public ResponseEntity<Resource> serveBoja(@PathVariable(name = "bojaId") final Integer bojaId) {

        ColorPaleta boja = colorPaletaService.findFirstById(bojaId);
        String filename = boja.getFilename();
        Resource file = storageService.loadAsResource(boja.getProizvod().getId(), filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }
    
    @GetMapping(value = "/photo/{proizvodId}/{photoId}")
    public ResponseEntity<Resource> servePhotoProizvod(@PathVariable(name = "proizvodId") final Integer proizvodId,
            @PathVariable(name = "photoId") final Integer photoId
    ) {

        Photo photo = photoService.findFirstById(photoId);

        String filename = photo.getFilename();
        Resource file = storageService.loadAsResource(proizvodId, filename);
        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(file);
    }

    @Autowired
    PhotoService photoService;

    @Autowired
    StorageService storageService;

    @Autowired
    ProizvodiService proizvodiService;

    @GetMapping(value = "/proizvod/{proizvodId}")
    public String publicProizvodMargotekstil(final Model model,
            @PathVariable final Integer proizvodId
    ) {
        model.addAttribute("proizvod", proizvodiService.findFirstById(proizvodId));
        List<Proizvodi> first3 = proizvodiService.findFirstFew(4);
        hocemoSamoTri(first3, proizvodId);
        model.addAttribute("prvaTriProizvoda", first3);
        
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().equals("anonymousUser")) {
                   return "neregistrovani/proizvod";
        }
        
        return "main/proizvod";
    }

    void hocemoSamoTri(List<Proizvodi> first3, Integer proizvodId) {
        if (first3.get(0).getId() == proizvodId) {
            first3.remove(first3.get(0));
        } else {
            if (first3.get(1).getId() == proizvodId) {
                first3.remove(first3.get(1));
            } else {
                if (first3.get(2).getId() == proizvodId) {
                    first3.remove(first3.get(2));
                } else {
                    first3.remove(first3.get(2));
                }
            }
        }

    }

    @GetMapping(value = "/registracija")
    public String publicRegistrationMargotekstil(final Model model) {

        return "main/registracija";
    }

    @GetMapping(value = "/error404")
    public String publicErrorMargotekstil(final Model model) {

        return "main/error404";
    }

    @GetMapping(value = "/dekorativniProgram")
    public String publicDekorativniProgramMargotekstil(final Model model) {

        return "main/dekorativniProgram";
    }

    @GetMapping(value = "/petmania")
    public String publicPetmaniaMargotekstil(final Model model) {

        return "main/petmania";
    }

    @GetMapping(value = "/horeca-tekstil")
    public String publicHorecaTekstilMargotekstil(final Model model) {

        return "main/horeca-tekstil";
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersService userService;
    @Autowired
    private KorpaService korpaService;

    //  @PostMapping(value = "/posaljiPoruku")
    @RequestMapping(value = "/posaljiPoruku", method = RequestMethod.POST)
    public String posaljiPoruku(final Model model,
            final HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "prezime") String prezime,
            @RequestParam(name = "ime") String ime,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "telefon") String telefon,
            @RequestParam(name = "poruka") String poruka
    ) {
        try {
            EmailController.SendEmailPoruka(ime, prezime, email, telefon, poruka);
            EmailController.SendEmailPorukaPoslata(email, ime, prezime);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

            return "redirect:/";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(final Model model, final HttpServletRequest request,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "prezime") String prezime,
            @RequestParam(name = "ime") String ime,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String lozinka,
            @RequestParam(name = "lozinkaRepeat") String lozinkaRepeat,
            @RequestParam(name = "adresa") String adresa,
            @RequestParam(name = "mesto") String mesto,
            @RequestParam(name = "postanskibroj") String postanskibroj,
            @RequestParam(name = "telefon") String telefon
    ) {
 if(userService.findFirstByEmail(email)!=null){
 redirectAttributes.addFlashAttribute("errorMessage", "Korisnik sa unetom email adresom već postoji.");

                return "redirect:/registracija";
 }
 
        Users user = new Users();
        user.setIme(ime);
        user.setPrezime(prezime);
        user.setEmail(email);

        user.setAdresa(adresa);
        user.setPostanski_broj(postanskibroj);
        user.setMesto(mesto);
        user.setBroj_telefona(telefon);
        user.setRole("SHOPPER");

        if (lozinka.equals(lozinkaRepeat)) {
            if (lozinka.length() >= 8) {
                user.setPassword(bCryptPasswordEncoder.encode(lozinka));
                try {
                    Korpa korpa = new Korpa();

                    korpaService.save(korpa);
                    user.setKorpa(korpa);
                    userService.save(user);
                    EmailController.SendEmailRegistracija(user);
                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

                    return "redirect:/registracija";
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Lozinka mora imati najmanje 8 karaktera");

                return "redirect:/registracija";
            }

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Ponovljena lozinka nije ista kao lozinka");

            return "redirect:/registracija";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Uspešno ste registrovali novi nalog.");

        return "redirect:/registracija";
    }

    @Autowired
    private ResetTokeniService resetTokeniService;

    @PostMapping(value = "/resetPassword")
    public String publicResetPasswordMargotekstil(final Model model,
            @RequestParam(name = "email") String email,
            RedirectAttributes redirectAttributes
    ) {
   
        if (userService.findFirstByEmail(email)==null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Korisnik sa unetim emailom ne postoji.");
            return "redirect:/registracija";
        }
        
 Users user = userService.findFirstByEmail(email);
        ResetTokeni resetTokeni = new ResetTokeni();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 30;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        resetTokeni.setVrednost(generatedString);
        resetTokeni.setEmail(email);
        resetTokeniService.save(resetTokeni);
        try {
            EmailController.SendResetPassword(email, generatedString, user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        redirectAttributes.addFlashAttribute("successMessage", "Poslat vam je link na email za resetovanje lozinke.");

        return "redirect:/registracija";
    }

    @GetMapping(value = "/resetPassword")
    public String resetPasswordMargotekstil(final Model model) {

        return "main/resetPassword";
    }

    @GetMapping(value = "/resetPassword/{resetToken}")
    public String resetPassword2Margotekstil(final Model model,
            @PathVariable final String resetToken
    ) {
        ResetTokeni resetTokeni = resetTokeniService.findFirstByVrednost(resetToken);
        if (resetTokeni != null) {
            model.addAttribute("email", resetTokeni.getEmail());
        }
        resetTokeniService.delete(resetTokeni);
        return "main/resetPassword";
    }

    @PostMapping(value = "/resetPassword/{email}")
    public String resetPassword3Margotekstil(final Model model,
            RedirectAttributes redirectAttributes,
            @PathVariable final String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "passwordrepeat") String passwordrepeat
    ) {

        if (password.equals(passwordrepeat)) {
            if (password.length() >= 8) {
                Users user = userService.findFirstByEmail(email);
                user.setPassword(bCryptPasswordEncoder.encode(password));
                try {
                    userService.save(user);

                } catch (Exception e) {
                    redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());

                    return "redirect:/registracija";
                }
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Lozinka mora imati najmanje 8 karaktera");

                return "redirect:/registracija";
            }

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Ponovljena lozinka nije ista kao lozinka");

            return "redirect:/registracija";
        }
        redirectAttributes.addFlashAttribute("successMessage", "Lozinka je uspešno promenjena.");

        return "redirect:/registracija";
    }

    @Autowired
    private ZavrsenePorudzbineService zavrsenePorudzbineService;

    @GetMapping("/istorijaKupovine")
    public String istorija(Model model,
            RedirectAttributes redirectAttributes
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();

        Users user = userService.findFirstByEmail(myUser.getEmail());
        model.addAttribute("userLogedIn", user);

        List<ZavrsenePorudzbine> sveporudzbine = zavrsenePorudzbineService.findAllByUser(user);
        List<Proizvodi> kupljeniproizvodi = new ArrayList();

        //pravimo listu svih kupljenih proizvoda
        for (ZavrsenePorudzbine zavrsenaporudzbina : sveporudzbine) {
            List<KorpaProizvodi> korpaproizvodi = zavrsenaporudzbina.getKorpa().getKorpaproizvodi();
            for (KorpaProizvodi korpaProizvod : korpaproizvodi) {

                if (!kupljeniproizvodi.contains(korpaProizvod.getProizvod())) {
                    kupljeniproizvodi.add(korpaProizvod.getProizvod());
                };

            }
        }
        
     model.addAttribute("kupljeniproizvodi", kupljeniproizvodi);     

        return "/main/istorijaKupovine";
    }
}
