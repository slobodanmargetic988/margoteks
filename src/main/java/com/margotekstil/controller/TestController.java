/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.margotekstil.controller;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.margotekstil.configuration.MargotekstilUserPrincipal;
import com.margotekstil.model.Users;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Aleksandra
 */
@Scope(WebApplicationContext.SCOPE_REQUEST)
@Controller
public class TestController {

    @GetMapping("/test")
    public String testPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            Users myUser = ((MargotekstilUserPrincipal) authentication.getPrincipal()).getUser();
            model.addAttribute("user", myUser);
        }

        return "main/test";
    }

    
    
      @GetMapping(value = "/QR")
    public ResponseEntity<byte[]> serveQRasphoto() {
        try{
    
ByteArrayOutputStream baos = new ByteArrayOutputStream();
ImageIO.write(generateQRCodeImage( "emobilnost.rs") , "jpg", baos);
   byte[] imageInByte = baos.toByteArray();   

        return ResponseEntity
                .ok()
                 .header(HttpHeaders.CONTENT_DISPOSITION, "filename=\"image.jpg\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageInByte);
    }catch(Exception e){
    return null;}
    }

    public static BufferedImage generateQRCodeImage(String barcodeText) throws Exception {

        QRCodeWriter barcodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = barcodeWriter.encode(barcodeText, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);

    }
    
public static String IPSQRStringGenerator(String racunPrimaoca,String nazivPrimaoca, Double sumazaplacanje,String sifraplacanja, String svrhaplacanja) throws Exception {
String ceoQRtekst="K:PR|V:01|C:1";

//racun primaoca mora biti 18 kataktera
String racunprimaocaKorektan=racunPrimaoca;
if(racunPrimaoca.length()!=18){
int dodatinula=18-racunPrimaoca.length();
if (dodatinula<0){//ako je racun veci od 18 karaktera nije moguce srediti ga
    throw new Exception("racun primaoca je predugacak");
}
String dodatnenule="";
for (int i=0;i<dodatinula;i++){
dodatnenule+="0";
}
racunprimaocaKorektan=racunPrimaoca.substring(0, 3) + dodatnenule + racunPrimaoca.substring(3);
}
//korekcija broja racuna gotova
ceoQRtekst+="|R:"+racunprimaocaKorektan;


//naziv primaoca sme imati do 70 karaktera i do dva nova reda tj tri reda ukupno
String nazivPrimaocaKorektanPoduzini=nazivPrimaoca;
if (nazivPrimaoca.length()>70){
nazivPrimaocaKorektanPoduzini=nazivPrimaoca.substring(0, 68);
//nazivPrimaocaKorektan.
}
String nazivPrimaocaKorektan="";
int brojacRedova=0;
Scanner scanner = new Scanner(nazivPrimaocaKorektan);
while (scanner.hasNextLine()) {
  String line = scanner.nextLine();
  brojacRedova++;
  if (brojacRedova<4){
  nazivPrimaocaKorektan+=line;}
  // process the line}
}
scanner.close();
//korekcija primaoca gotova
ceoQRtekst+="|N:"+nazivPrimaocaKorektan;


//suma za placanje # bez razmaka #mora da posinje sa RSD #mora da ima zarez a ne tacku #ne sme da bude prvo zarez bez nule ispred #ne sme da bude vise od dve cifre posle zareza#sme da ne bude nista iza zareza# mora da bude zarez i ako nema nista iza # NE SME DA IMA RAZMAKE
String sumazaplacanjekorektna="RSD";
DecimalFormat df = new DecimalFormat("#.00");
sumazaplacanjekorektna+=df.format(sumazaplacanje);
sumazaplacanjekorektna = sumazaplacanjekorektna.replace(".", ",");
sumazaplacanjekorektna = sumazaplacanjekorektna.replace(" ", "");
//suma za placanje gotova
ceoQRtekst+="|I:"+sumazaplacanjekorektna;


//sifra placanja
String sifraplacanjakorektna=sifraplacanja;
char ch1 = sifraplacanjakorektna.charAt(0);
if (!sifraplacanjakorektna.startsWith("1")){   throw new Exception("sifra placanja treba da pocinje sa 2 sto oznacava bezgotovinsko placanje"); }
String[] extn={"22","23","24","25","26","27","28","31","40","41","42","44","45","46","47","48","49","53","54","57","58","60","61","62","63","64","65","66","70","71","72","73","75","76","77","78","79","80","81","82","83","84","85","86","87","88","89","90"};
boolean dobrasifra=Arrays.stream(extn).anyMatch(entry -> sifraplacanjakorektna.endsWith(entry));
if (!dobrasifra){ throw new Exception("sifra placanja je nepostojeca probajte sa 221-promet robe i usluga - finalna potrosnja");}
//sifra placanja gotova
ceoQRtekst+="|SF:"+sifraplacanjakorektna;
 
//svrha placanja
String svrhaplacanjakorektna=svrhaplacanja;
if (svrhaplacanjakorektna.length()>33){
svrhaplacanjakorektna=svrhaplacanja.substring(0, 33);
svrhaplacanjakorektna = svrhaplacanjakorektna.replace("\n", "");
svrhaplacanjakorektna = svrhaplacanjakorektna.replace("\r", "");
}
//svrha placanja gotova
ceoQRtekst+="|S:"+sifraplacanjakorektna;






        return ceoQRtekst;

    }
}
