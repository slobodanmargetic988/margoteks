
$(document).ready(function () {



    //Highlight current category menu item
    $(function () {
        let url = window.location.href;
        $('#myCategory li a').each(function () {
            if (this.href === url) {
                $(this).addClass('currentCategoryItem');
            }
        });
    });



    /*klik bilo gde na ekranu da se zatvori popup proizvoda koji je dodat u korpu*/
    var modal = document.getElementById('addedToCart-window');
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
        ;
    };

//na dugme Nazad da ne refresuje vec samo fade out da nestane - u popup kad se dodaje proizvod na shop strani
    $("#nazad-na-shop-btn").on("click", function () {
        $("#addedToCart-window").fadeOut();
    });

    $(".plus").on("click", function (e) {

        e.preventDefault();
        // Define field variable
        field = $("#kolicina");
        var currentVal = parseInt($(field).val());
        $(field).val(currentVal + 1);
    });

    $(".minus").on("click", function (e) {
        e.preventDefault();
        // Define field variable
        field = $("#kolicina");
        var currentVal = parseInt($(field).val());
        if (!isNaN(currentVal) && currentVal > 1) {
            $(field).val(currentVal - 1);
        }
    });

    $(".JSdodajUKorpuProizvodStranaNEREG").on("click", function (e) {
        // prikaziPopup(this);
        var kolicina = parseInt($("#kolicina").val());
        dodajUCookieKorpu(this, kolicina);
    });

    $(".JSdodajUKorpuProizvodStranaNEREGSlicni").on("click", function (e) {
        // prikaziPopup(this);
        var kolicina = 1;
        dodajUCookieKorpu(this, kolicina);
    });


    $("#predzadnjiKorak").on("click", function (e) {
        $("#zadnjiKorakIme").text($("#predzadnjiKorakIme").val());
        $("#zadnjiKorakPrezime").text($("#predzadnjiKorakPrezime").val());
        $("#zadnjiKorakUlica").text($("#predzadnjiKorakAdresa").val());
        $("#zadnjiKorakPB").text($("#predzadnjiKorakPB").val());
        $("#zadnjiKorakGrad").text($("#predzadnjiKorakGrad").val());
        $("#zadnjiKorakTelefon").text($("#predzadnjiKorakTelefon").val());
        $("#zadnjiKorakNapomena").text($("#predzadnjiKorakNapomena").val());
        $("#zadnjiKorakNacinPlacanja").text($("#nacinPlacanja").val());
        $("#zadnjiKorakEmail").text($("#predzadnjiKorakEmail").val());
        $('.kolicinaPregledJS').each(function () {
            $(this).text($('.kolicinaJS[pid="' + $(this).attr("pid") + '"]').val());
        });
    });
});

function prikaziPopup(element, vecukorpi) {
    var id = $(element).attr("pid");
    var photo = $(element).attr("photoid");
    var naziv = $(element).attr("naziv");
    var cena = $(element).attr("cena");
    // $("#popupnaziv").text(naziv);
    //  $("#popupimg").attr("src", "/photo" + "/" + id + "/" + photo);
    //   $("#popupcena").text(cena);
    $("#addedToCart-window").css("display", "block");
    if (vecukorpi) {
        $("#ProizvodDodatJS").text("Proizvod je već u korpi");
    } else {
        $("#ProizvodDodatJS").text("Proizvod je dodat u korpu");
    }
}

function skloniIzCookieKorpe(id) {
    var celaKorpa = JSON.parse(Cookies.get('korpa'));
    for (var i = 0; i < celaKorpa.length; i++) {
        if (celaKorpa[i].idProizvoda === id) {
            celaKorpa.splice(i, 1);
            break;
        }
    }
    var celaKorpaJSON = JSON.stringify(celaKorpa);
    Cookies.set('korpa', celaKorpaJSON);
}

function dodajUCookieKorpu(element, kolicina) {
    var id = $(element).attr("pid");
    var photo = $(element).attr("photoid");
    var naziv = $(element).attr("naziv");
    var cena = $(element).attr("cena");
    var opis = $(element).attr("opis");
    var kilaza = $(element).attr("kilaza");

    //pravimo objekat korpa stavke da bi mogao lepo da se parsuje u i iz JSON
    var korpaStavka = new Object();
    korpaStavka.idProizvoda = id;
    korpaStavka.photoId = photo;
    korpaStavka.nazivProizvoda = naziv;
    korpaStavka.cena = cena;
    korpaStavka.kolicina = kolicina;
    korpaStavka.opis = opis;
    korpaStavka.kilaza = kilaza;
//ako colicic vec postoji pravimo niz korpa stavki iz kolacica pa dodajemo trenutnu stavku
    if (Cookies.get('korpa') !== undefined) {
        var celaKorpa = JSON.parse(Cookies.get('korpa'));
        //proveravamo da li je proizvod vec u kolacicima da ga ne dupliramo
        var notfound = true;
        var foundindex = 0;
        for (var i = 0; i < celaKorpa.length; i++) {
            if (celaKorpa[i].idProizvoda === id) {
                notfound = false;
                foundindex = i;
                break;
            }
        }
        if (notfound) {//ako nije pronadjen dodajemo u korpu
            celaKorpa.push(korpaStavka);
            prikaziPopup(element, false);
        } else {//ako jeste pronadjen mozda menjamo kolicinu
            celaKorpa[foundindex].kolicina = kolicina;
            prikaziPopup(element, true);
        }
    } else {//ako colicic vec ne postoji pravimo niz korpa stavki iz jedne trenutne stavke da bi se posle parsovao kao niz 
        var celaKorpa = [korpaStavka];
    }
    //korpu pretvaramo u JSON ARRAY opet i cuvamo u kolacice u tom lepom obliku
    var celaKorpaJSON = JSON.stringify(celaKorpa);
    Cookies.set('korpa', celaKorpaJSON);

    var mojobj = JSON.parse(Cookies.get('korpa'));
    console.log(mojobj);
}

function updateCookie(id, kolicina) {
    var celaKorpa = JSON.parse(Cookies.get('korpa'));
    for (var i = 0; i < celaKorpa.length; i++) {
        if (celaKorpa[i].idProizvoda === id) {
            celaKorpa[i].kolicina = kolicina;
            break;
        }
    }
    var celaKorpaJSON = JSON.stringify(celaKorpa);
    Cookies.set('korpa', celaKorpaJSON);

    var mojobj = JSON.parse(Cookies.get('korpa'));
    console.log(mojobj);
}



function updateTotals() {
    $('.totalcenaproizvoda').each(function () {
        pid = $(this).attr("pid");
        cena = parseFloat($('.cenaproizvoda[pid="' + pid + '"]').attr("kosta"));
        kolicina = $('.kolicinaJS[pid="' + pid + '"]').val();
        $(this).text((cena) * kolicina);
    });
    $('.totalkilazaproizvoda').each(function () {
        pid = $(this).attr("pid");
        kila = parseFloat($('.kilazaJS[pid="' + pid + '"]').val());
        kolicina = $('.kolicinaJS[pid="' + pid + '"]').val();
        $(this).text((kila * kolicina).toFixed(2));

        $(this).attr("tezi", (kila) * kolicina);
    });
    suma = 0;
    $('.totalcenaproizvoda').each(function () {
        suma += parseFloat($(this).text()); // Or this.innerHTML, this.innerText
    });
    kilaza = 0;

    $('.totalkilazaproizvoda').each(function () {
        kilaza += parseFloat($(this).text()); // Or this.innerHTML, this.innerText

    });
    if (suma == 0) {
        $('.full-cartJS').css("display", "none");
        $('.empty-cart').css("display", "block");
    }

    $('#korpatotal').text((suma).toFixed(2) + " RSD");
    $('#totalkilaza').text((kilaza).toFixed(2) + " kg");

    postarina = 0;
    if (kilaza <= 0.5) {
        postarina = 300;
    }
    if (kilaza >= 0.5 && kilaza < 1) {
        postarina = 350;
    }
    if (kilaza >= 1 && kilaza < 2) {
        postarina = 420;
    }
    if (kilaza >= 2 && kilaza < 5) {
        postarina = 530;
    }
    if (kilaza >= 5) {
        postarina = 700;
    }
    $('#postarinatotal').text(postarina + " RSD");
}

function napraviKorpaStavke() {

    var stavke = "";
    if (Cookies.get('korpa') !== undefined) {

        var celaKorpa = JSON.parse(Cookies.get('korpa'));
        for (var i = 0; i < celaKorpa.length; i++) {


            var stavka = " <div class=\"row border-top border-bottom stavkaJS korpaStavkeSmallerScreen\" pid=\"" + celaKorpa[i].idProizvoda + "\">"
                    + " <div class=\"row main align-items-center mt-2 mb-2\">"
                    + " <div class=\"col-4 slikaStavkaSmallerScreen\"><img class=\"img-fluid slika-korpaStavka slika-korpaStavkaSmallerScreen\"  src=\"/photo/" + celaKorpa[i].idProizvoda + "/" + celaKorpa[i].photoId + "\"  alt=\"" + celaKorpa[i].alt_text + "\"  title=\"" + celaKorpa[i].title + "\"></div>"
                    + "  <div class=\"col nazivOpisSmallerScreen\">"
                    + "<div class=\"row naslov\">" + celaKorpa[i].nazivProizvoda + "</div>"
                    + " <div class=\"row opis-proizvoda\">" + celaKorpa[i].opis + "</div>"
                    + " </div>"
                    + " <div class=\"col minusiplus minusiplusSmallerScreen\">"
                    + "  <input type='button'  value='-' class='minusBtn minusBtnSmallerScreen smanjiJS' field='quantity'  pid=\"" + celaKorpa[i].idProizvoda + "\" />"
                    + "  <input  type='number' name='quantity' value='" + celaKorpa[i].kolicina + "' class='qty qtySmallerScreen border-number kolicinaJS'  pid=\"" + celaKorpa[i].idProizvoda + "\" />"
                    + " <input type='button'  value='+' class='plusBtn plusBtnSmallerScreen povecajJS' field='quantity'  pid=\"" + celaKorpa[i].idProizvoda + "\"/>"
                    + " <input  type='number' name='kilaza' value='" + celaKorpa[i].kilaza + "' class='qty border-number kilazaJS'  pid=\"" + celaKorpa[i].idProizvoda + "\" style=\"display: none\" />"
                    + " </div>"
                    + "  <div class=\"col cenaKilazaSmallerScreen\"><div class=\"cenapr cenaprSmallerScreen\">Cena: </div><span class=\"cenaproizvoda\" pid=\"" + celaKorpa[i].idProizvoda + "\" kosta=" + celaKorpa[i].cena + "\">" + celaKorpa[i].cena + "</span> <span style=\"font-size: .95rem;font-weight: 700;\"> RSD</span>"
                    + "  <div class=\"cenapr cenaprSmallerScreen\">Ukupna cena: </div><span class=\"totalcenaproizvoda\" pid=\"" + celaKorpa[i].idProizvoda + "\"></span> <span style=\"font-size: .95rem;font-weight: 700;\">RSD</span>"
                    + "  <div class=\"cenapr cenaprSmallerScreen\">Ukupna kilaza: </div>"
                    + " <span class=\"totalkilazaproizvoda\" pid=\"" + celaKorpa[i].idProizvoda + "\" tezi=\"0\"></span> <span style=\"font-size: .95rem;font-weight: 700;\">kg</span>"
                    + " </div>"
                    + " <span class=\"skloni\"  pid=\"" + celaKorpa[i].idProizvoda + "\" >&#10005;&nbsp;<span class=\"ukloniSmallerScreen\">Ukloni</span></span>"
                    + " </div>"
                    + "  </div>";
            stavke += stavka;
        }
    }
    return stavke;
}



