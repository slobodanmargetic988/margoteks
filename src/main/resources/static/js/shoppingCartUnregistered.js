
$(document).ready(function () {




    $("#korpaStavkeNEREG").append(napraviKorpaStavke());
    updateTotals();

    $(".skloni").on("click", function (e) {
        id = $(this).attr("pid");
 boja = $(this).attr("boja");

        $('.stavkaJS[pid="' + id + '"][boja='+boja+']').slideUp("slow", function () {
            $('.stavkaJS[pid="' + id + '"][boja="'+boja+'"]').remove();
            skloniIzCookieKorpe(id,boja);
            updateTotals();
        });
    });

    $(".smanjiJS").on("click", function (e) {


        field = $('.kolicinaJS[pid="' + $(this).attr("pid") + '"]');
        var currentVal = parseInt($(field).val());
        if (!isNaN(currentVal) && currentVal > 1) {
            $(field).val(currentVal - 1);
            updateTotals();
            updateCookie($(this).attr("pid"), currentVal - 1);
        }

    });



    $(".povecajJS").on("click", function (e) {
        field = $('.kolicinaJS[pid="' + $(this).attr("pid") + '"]');
        var currentVal = parseInt($(field).val());
        $(field).val(currentVal + 1);
        updateTotals();
        updateCookie($(this).attr("pid"), currentVal + 1);
    });

    $("#predzadnjiKorak").on("click", function (e) {

        $("#korpaStavkePregledNEREG").append(napraviKorpaStavkePregled());
    });
    $(".JSprevious").on("click", function (e) {

        $('.stavkaPregledJS').remove();
    });



    $(".JSzadnjiKorak").on("click", function (e) {
        var celaKorpaJSON = Cookies.get('korpa');
        console.log(celaKorpaJSON);
        if (celaKorpaJSON !== "[]") {
            var korisnik = new Object();
            korisnik.ime = $("#predzadnjiKorakIme").val();
            korisnik.prezime = $("#predzadnjiKorakPrezime").val();
            korisnik.telefon = $("#predzadnjiKorakTelefon").val();
            korisnik.email = $("#predzadnjiKorakEmail").val();
            korisnik.adresa = $("#predzadnjiKorakAdresa").val();
            korisnik.postanskibroj = $("#predzadnjiKorakPB").val();
            korisnik.grad = $("#predzadnjiKorakGrad").val();
            korisnik.nacinplacanja = $("#nacinPlacanja").val();
            korisnik.napomena = $("#predzadnjiKorakNapomena").val();
            var korisnikJSON = JSON.stringify(korisnik);
            Cookies.set('korisnik', korisnikJSON);

            var celaKorpa = JSON.parse(Cookies.get('korpa'));

            var myData = {
                "korisnik": korisnik,
                "korpa": celaKorpa
            };

            url = "/korpa/zavrsiPorudzbinu/neregistrovan";
            $.ajax({
                type: "GET",
                contentType: 'application/json; charset=utf-8',
                url: url,
                data: {"myData": JSON.stringify(myData)},

                cache: false,
                timeout: 600000,
                success: function (response) {
                    // alert(response);
                    if (response == "Illegal address") {
                        alert("Uneti email nije ispravan");
                    } else {
                        if (response == "For input string: \"\"") {
                            alert("Uneti broj telefona ili poštanski broj nisu ispravni");

                        } else {
                            $("#alertHvalaPorudzbina").css("display", "block");
                            Cookies.set('korpa', "[]");
                        }
                    }
                },
                error: function (e) {
                  //  alert(e);
                     console.log(e);
                }
            });
        } else {
            alert("Porudžbina je već poslata");
        }

    });

});




function napraviKorpaStavkePregled() {
      var stavke = "";
     if (Cookies.get('korpa') !== undefined){
    var celaKorpa = JSON.parse(Cookies.get('korpa'));
 var bojatekst="";
    for (var i = 0; i < celaKorpa.length; i++) {

 if (celaKorpa[i].boja != 0) {
                bojatekst=" <img class=\"color-circle\" src=\"/boja/" + celaKorpa[i].boja + "\" > ";
            }
            else{
                 bojatekst=" <span>Boja: osnovna</span>" ;
                
            }

        var stavka = "<div class=\"row border-top border-bottom stavkaPregledJS korpaStavkeSmallerScreen\" pid=\"" + celaKorpa[i].idProizvoda + "\">"
                + "            <div class=\"row main align-items-center mt-2 mb-2 w-100\">"
                + "           <div class=\"col-4 slikaStavkaSmallerScreen\"><img class=\"img-fluid slika-korpaStavkaPregled  slika-korpaStavkaSmallerScreen\"  src=\"/photo/" + celaKorpa[i].idProizvoda + "/" + celaKorpa[i].photoId + "\" alt=\"" +celaKorpa[i].alt_text+"\"  title=\"" +celaKorpa[i].title+"\"></div>"
                + "          <div class=\"col-2 nazivOpisSmallerScreen\">"                                  
                + "          <div class=\"row naslov\"><span>" + celaKorpa[i].nazivProizvoda + "</span></div>"
          + "         </div>"
      + "<div class=\"col-3 product-color-basket product-color-basketSmallerScreen\">"
                    +bojatekst
                    +"</div>"
        
              
                + "         <div class=\"cenaprSmallerScreenStavkaPregled\">"
                + "        <div class=\"col cenaprSmallerScreen cenaprSmallerScreenStavkaPregled\" style=\"font-weight: 500\">Kolicina:  <span style=\"font-weight: 600\" class=\"kolicinaPregledJS\" >" + celaKorpa[i].kolicina + " </span> </div>"
                + "        <div class=\"col cenaprSmallerScreen cenaprSmallerScreenStavkaPregled\" style=\"font-weight: 500; padding-bottom: 1rem;\">Cena: <br><span style=\"font-weight: 600\" >" + celaKorpa[i].cena + "</span> RSD </div>"
                + "        </div>"
                + "        </div>"
                + "        </div>";
        stavke += stavka;
    }
     }
    return stavke;
}

