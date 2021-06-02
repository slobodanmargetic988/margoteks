/* Ovo je bilo pre u okviru fajla shopFunctionalities.js, ali tamo nije radilo, nesto ga je gazilo, pa sam napravila novi fajl u kome
 ce se nalaziti ovo. Sada je ovo includovano samo na home strani */

$(document).ready(function () {
    
        //alert za uspesnu porudzbinu da nestane nakon nekoliko sekundi  - sa home strane 
    setTimeout(function () {
        $("#alertThankYouPorudzbina").fadeOut(400);
    }, 3500);
    

//klik na ok dugme, pa da alert za uspesnu kupovinu nestane.
     $(".OKBtn").on("click", function () {
        $("#alertThankYouPorudzbina").fadeOut();
    });


    
    });