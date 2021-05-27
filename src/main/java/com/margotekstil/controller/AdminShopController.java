/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.controller;

import com.margotekstil.model.Photo;
import com.margotekstil.model.Proizvodi;
import com.margotekstil.service.PhotoService;
import com.margotekstil.service.ProizvodiService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.margotekstil.service.UsersService;
import com.margotekstil.storage.StorageService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.context.annotation.Scope;
import org.springframework.core.SpringVersion;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Aleksandra
 */
//@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
public class AdminShopController {
    
     
     @GetMapping("/admin/adminShopPregled")
    public String adminShopPregled(Model model) {
        return "main/admin/adminShopPregled";
    }


    @GetMapping(value = "/admin/noviProizvod")
    public String adminShopNoviProizvod(
            final Model model) {
        model.addAttribute("listaProizvoda", proizvodiService.findAllBy());

        return "main/admin/adminNoviProizvod";
    }

    @PostMapping(value = "/admin/noviProizvod/save")
    public String adminShopNoviProizvodSave(
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "ime", required = true) String ime,
            @RequestParam(name = "opis", defaultValue = "") String opis,
            @RequestParam(name = "keywords", defaultValue = "") String keywords,
            @RequestParam(name = "kategorija", defaultValue = "") String kategorija,
            @RequestParam(name = "cena", defaultValue = "0") Double cena,
            @RequestParam(name = "pdv", defaultValue = "0") Double pdv,
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "slicniProizvod1", defaultValue = "1") Integer slicniProizvod1,
            @RequestParam(name = "slicniProizvod2", defaultValue = "3") Integer slicniProizvod2,
            @RequestParam(name = "slicniProizvod3", defaultValue = "4") Integer slicniProizvod3,
            @RequestParam(name = "dimenzije", defaultValue = "0") String dimenzije,
            @RequestParam(name = "kilaza", defaultValue = "0") Double kilaza
    ) {
        String proizvodId = "";
        try {
            Proizvodi proizvod = new Proizvodi();

            Proizvodi s1 = proizvodiService.findFirstById(slicniProizvod1);
            Proizvodi s2 = proizvodiService.findFirstById(slicniProizvod2);
            Proizvodi s3 = proizvodiService.findFirstById(slicniProizvod3);
            List<Proizvodi> slicni = new ArrayList();
            slicni.add(s1);
            slicni.add(s2);
            slicni.add(s3);

            //  Proizvodi proizvod = new Proizvodi();
            proizvod.setIme(ime);
            proizvod.setOpis(opis);
            proizvod.setKategorija(kategorija);
            proizvod.setKeywords(keywords);
            proizvod.setCena(cena);
            proizvod.setPdv(pdv);
            proizvod.setActive(Boolean.TRUE);
            proizvod.setGlavnaslika(null);
            proizvod.setSlicniproizvodi(slicni);
            proizvod.setDimenzije(dimenzije);
            proizvod.setKilaza(kilaza);
      if (!file.isEmpty()) {
            proizvodiService.save(proizvod);
      
                String filename = storageService.store(file, proizvod.getId());

                Photo photo = new Photo();
                photo.setFilename(filename);
                photo.setTitle(title);
                photo.setGlavnazaproizvod(proizvod);
                photo.setActive(Boolean.TRUE);
                photoService.save(photo);

                proizvod.setGlavnaslika(photo);
                proizvodiService.save(proizvod);
                proizvodId += proizvod.getId();
                 redirectAttributes.addFlashAttribute("successMessage", "Proizvod je uspešno dodat!");
            }else{
        redirectAttributes.addFlashAttribute("errorMessage", "proizvod nije uspešno dodat, morate izabrati sliku! " );
              return "redirect:/admin/noviProizvod" ;
      }
           
            //  redirectAttributes.addFlashAttribute("errorMessage", ("proizvod nije uspesno dodat!"));
        } catch (Exception e) {
            //  System.out.println(e);
            redirectAttributes.addFlashAttribute("errorMessage", ("proizvod nije uspešno dodat! " + e.getMessage()));
              return "redirect:/admin/noviProizvod" ;
        }
        return "redirect:/admin/proizvod/" + proizvodId;
        //     return "redirect:/admin/noviProizvod";
        // return "main/admin/adminNoviProizvod";
    }

    @GetMapping(value = "/admin/galerija")
    public String adminGalerijaMargotekstil(final Model model, @PageableDefault(value = 12) final Pageable pageable) {

        model.addAttribute("listaSlika", photoService.findByProizvodIsNullAndGlavnazaproizvodIsNullAndActive(true,pageable));

        return "main/admin/adminGalerija";
    }

    @GetMapping(value = "/admin/obrisiSliku/{photoId}")
    public String adminGalerijaObrisiMargotekstil(final Model model,
            @PathVariable final Integer photoId,
            RedirectAttributes redirectAttributes
    ) {
        Photo photo = photoService.findFirstById(photoId);
        photo.setActive(Boolean.FALSE);
        try {
            photoService.save(photo);
            redirectAttributes.addFlashAttribute("successMessage", "Slika je uspešno uklonjena!");

        } catch (Exception e) {
            //  System.out.println(e);
            redirectAttributes.addFlashAttribute("errorMessage", ("Slika nije uspešno uklonjena! " + e.getMessage()));
        }

        model.addAttribute("listaSlika", photoService.findAllBy());

        return "redirect:/admin/galerija";
        //     return "main/admin/galerija";
    }

    @GetMapping(value = "/admin/izmeniProizvod/{proizvodId}/dodajSliku")
    public String adminProizvodNovaSlikaMargotekstil(final Model model,
            @PathVariable final Integer proizvodId
    ) {
        model.addAttribute("proizvodId", proizvodId);
        return "main/admin/adminProizvodDodatnaSlika";
    }

    @GetMapping(value = "/admin/novaSlika")
    public String adminNovaSlikaMargotekstil(final Model model) {

        return "main/admin/adminNovaSlika";
    }

    @PostMapping(value = "/admin/novaSlika/{proizvodId}/save")
    public String adminDodatnaNovaSlikaProizvodSaveMargotekstil(final Model model,
            @PathVariable final Integer proizvodId,
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "title") String title,
            RedirectAttributes redirectAttributes
    ) {
        try {
            String filename = storageService.store(file, proizvodId);
            Photo photo = new Photo();
            photo.setFilename(filename);
            photo.setTitle(title);
            photo.setProizvod(proizvodiService.findFirstById(proizvodId));
            photo.setActive(Boolean.TRUE);
            photoService.save(photo);
            redirectAttributes.addFlashAttribute("successMessage", "Slika je uspešno dodata u listu slika za proizvod!");

        } catch (Exception e) {
            // System.out.println(e);
            redirectAttributes.addFlashAttribute("errorMessage", ("Slika nije uspešno dodata u listu slika za proizvod! Nevalidan tip fajla. " + e.getMessage()));

        }
        return "redirect:/admin/proizvod/" + proizvodId;
        //  return "main/admin/adminNovaSlika";
    }

    @Autowired
    PhotoService photoService;

    @Autowired
    StorageService storageService;

    @PostMapping(value = "/admin/novaSlika/save")
    public String adminNovaSlikaSaveMargotekstil(final Model model,
            @RequestParam("file") MultipartFile file,
            @RequestParam(name = "title") String title,
            RedirectAttributes redirectAttributes
    ) {
        try {
            String filename = storageService.store(file, 0);
            Photo photo = new Photo();
            photo.setFilename(filename);
            photo.setTitle(title);
            photo.setProizvod(null);
            photo.setActive(Boolean.TRUE);
            photoService.save(photo);
            redirectAttributes.addFlashAttribute("successMessage", "Slika je uspešno dodata u galeriju!");

        } catch (Exception e) {
            // System.out.println(e);
            redirectAttributes.addFlashAttribute("errorMessage", ("Slika nije uspešno dodata u galeriju! Nevalidan tip fajla. " + e.getMessage() ));

        }
        return "redirect:/admin/galerija";
        //  return "main/admin/adminNovaSlika";
    }

    @Autowired
    ProizvodiService proizvodiService;

    @GetMapping(value = "/admin/shop")
    public String adminShopMargotekstil(final Model model,@PageableDefault(value = 12) final Pageable pageable) {
    model.addAttribute("listakategorija", proizvodiService.findListaKategorija());
        model.addAttribute("listaProizvoda",  proizvodiService.findAllByActiveOrderByImeAsc(true,pageable));
model.addAttribute("trenutnaKategorija", "sveKategorije");
        return "main/admin/adminShop";
    }

    @GetMapping(value = "/admin/shop/{kategorija}")
    public String adminShopKategorijaMargotekstil(final Model model,
            @PageableDefault(value = 12) final Pageable pageable,
            @PathVariable final String kategorija
    ) {

        model.addAttribute("listakategorija", proizvodiService.findListaKategorija());
        model.addAttribute("listaProizvoda", proizvodiService.findByKategorijaOrderByActiveDescImeAsc(kategorija, pageable));
model.addAttribute("trenutnaKategorija", kategorija);
        return "main/admin/adminShop";
    }

    //  @Autowired
    //  ProizvodiService proizvodiService;
    @GetMapping(value = "/admin/obrisiProizvod/{proizvodId}")
    public String adminShopBrisiMargotekstil(final Model model,
            @PathVariable final Integer proizvodId,
            RedirectAttributes redirectAttributes
    ) {
        Proizvodi proizvod = proizvodiService.findFirstById(proizvodId);
        proizvod.setActive(Boolean.FALSE);
        try {
            proizvodiService.save(proizvod);
            redirectAttributes.addFlashAttribute("successMessage", "Proizvod je uspešno obrisan!");
        } catch (Exception e) {
            //  System.out.println(e);
            redirectAttributes.addFlashAttribute("errorMessage", ("Proizvod nije uspešno obrisan! " + e.getMessage()));
        }

        model.addAttribute("listaProizvoda", proizvodiService.findAllBy());

        return "redirect:/admin/shop";
        //return "main/admin/adminShop";
    }

    @GetMapping(value = "/admin/proizvod/{proizvodId}")
    public String admimProizvodMargotekstil(final Model model,
            @PathVariable final Integer proizvodId
    ) {
        model.addAttribute("proizvod", proizvodiService.findFirstById(proizvodId));
        List<Proizvodi> first3 = proizvodiService.findFirstFew(4);
        hocemoSamoTri(first3, proizvodId);
        model.addAttribute("prvaTriProizvoda", first3);
        return "main/admin/adminProizvod";
    }

    @GetMapping(value = "/admin/izmeniProizvod/{proizvodId}")
    public String admimProizvodIzmeniMargotekstil(final Model model,
            @PathVariable final Integer proizvodId,
            RedirectAttributes redirectAttributes
    ) {
        try {
//            Proizvodi proizvod = new Proizvodi();
//
//            proizvodiService.save(proizvod);
            redirectAttributes.addFlashAttribute("successMessage", "Proizvod je uspešno izmenjen!");
        } catch (Exception e) {
            //  System.out.println(e);
            redirectAttributes.addFlashAttribute("errorMessage", ("Proizvod nije uspešno izmenjen! " + e.getMessage()));
        }
        model.addAttribute("proizvod", proizvodiService.findFirstById(proizvodId));
        model.addAttribute("listaProizvoda", proizvodiService.findAllBy());

        return "main/admin/adminIzmeniProizvod";
    }

    @PostMapping(value = "/admin/izmeniProizvod/{proizvodId}/save")
    public String adminShopIzmeniProizvodSave(
            @PathVariable final Integer proizvodId,
            RedirectAttributes redirectAttributes,
            @RequestParam(name = "ime", required = true) String ime,
            @RequestParam(name = "opis", defaultValue = "") String opis,
            @RequestParam(name = "keywords", defaultValue = "") String keywords,
            @RequestParam(name = "kategorija", defaultValue = "") String kategorija,
            @RequestParam(name = "cena", defaultValue = "0") Double cena,
            @RequestParam(name = "pdv", defaultValue = "0") Double pdv,
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "slicniProizvod1", defaultValue = "1") Integer slicniProizvod1,
            @RequestParam(name = "slicniProizvod2", defaultValue = "3") Integer slicniProizvod2,
            @RequestParam(name = "slicniProizvod3", defaultValue = "4") Integer slicniProizvod3,
            @RequestParam(name = "dimenzije", defaultValue = "0") String dimenzije,
            @RequestParam(name = "kilaza", defaultValue = "0") Double kilaza
    ) {
        try {
            Proizvodi proizvod = proizvodiService.findFirstById(proizvodId);

            Proizvodi s1 = proizvodiService.findFirstById(slicniProizvod1);
            Proizvodi s2 = proizvodiService.findFirstById(slicniProizvod2);
            Proizvodi s3 = proizvodiService.findFirstById(slicniProizvod3);
            List<Proizvodi> slicni = new ArrayList();
            slicni.add(s1);
            slicni.add(s2);
            slicni.add(s3);

            //  Proizvodi proizvod = new Proizvodi();
            proizvod.setIme(ime);
            proizvod.setOpis(opis);
            proizvod.setKategorija(kategorija);
            proizvod.setKeywords(keywords);
            proizvod.setCena(cena);
            proizvod.setPdv(pdv);
            proizvod.setActive(Boolean.TRUE);
            proizvod.setGlavnaslika(null);
            proizvod.setSlicniproizvodi(slicni);
            proizvod.setDimenzije(dimenzije);
            proizvod.setKilaza(kilaza);

            proizvodiService.save(proizvod);
            if (!file.isEmpty()) {
                String filename = storageService.store(file, proizvod.getId());

                Photo photo = new Photo();
                photo.setFilename(filename);
                photo.setTitle(title);
                photo.setGlavnazaproizvod(proizvod);
                photo.setActive(Boolean.TRUE);
                photoService.save(photo);

                proizvod.setGlavnaslika(photo);
                proizvodiService.save(proizvod);

            }
            redirectAttributes.addFlashAttribute("successMessage", "Proizvod je uspešno izmenjen!");
            //  redirectAttributes.addFlashAttribute("errorMessage", ("proizvod nije uspesno dodat!"));
        } catch (Exception e) {
            //  System.out.println(e);
            redirectAttributes.addFlashAttribute("errorMessage", ("Proizvod nije uspešno izmenjen! " + e.getMessage()));
        }
        return "redirect:/admin/proizvod/" + proizvodId;
        // return "main/admin/adminNoviProizvod";
    }

// prosledjujemo listu proizvoda sa 4 proizvoda i izbacujemo jedan ako neki ima isti id kao proizvod ciju stranu prikazujemo. ako nijedan nema isti id izbacujemo zadnji da bi imali samo 3 u listi da to sve lepo izgleda.
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsersService userService;

}
