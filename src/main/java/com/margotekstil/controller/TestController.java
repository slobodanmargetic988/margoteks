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
import com.margotekstil.model.Photo;
import com.margotekstil.model.Users;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
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
    
     public static BufferedImage IPSQRGenerator(String racunPrimaoca,String nazivPrimaoca) throws Exception {
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
ceoQRtekst+="|R:"+racunprimaocaKorektan;
//korekcija broja racuna gotova


//naziv primaoca sme imati do 70 karaktera i do dva nova reda tj tri reda ukupno
String nazivPrimaocaKorektan=nazivPrimaoca;
if (nazivPrimaoca.length()>70){
nazivPrimaocaKorektan=racunPrimaoca.substring(0, 68);
//nazivPrimaocaKorektan.
}

//korekcija primaoca gotova

        QRCodeWriter barcodeWriter = new QRCodeWriter();

        BitMatrix bitMatrix = barcodeWriter.encode(ceoQRtekst, BarcodeFormat.QR_CODE, 200, 200);

        return MatrixToImageWriter.toBufferedImage(bitMatrix);

    }
}
