
jQuery(document).ready(function () {
    

 //Highlight current category menu item
    $(function () {
        let url = window.location.href;
        $('#myCategory li a').each(function () {
            if (this.href === url) {
                $(this).addClass('currentCategoryItem');
            }
        });
    });
    

/*
    $(".dodajuKorpu-shop-page").on("click", function (e) {
        photo = $(this).attr("photoid");
        naziv = $(this).attr("naziv");
        cena = $(this).attr("cena");
        proizvod = $(this).attr("proizvodid");

        $("#popupnaziv").text(naziv);
        $("#popupimg").attr("src", "/photo" + "/" + proizvod + "/" + photo);

        $("#popupcena").text(cena);
        $("#addedToCart-window").css("display", "block");
    });


    $(".dodajuKorpu-proizvod-page").on("click", function (e) {
        photo = $(this).attr("photoid");
        naziv = $(this).attr("naziv");
        cena = $(this).attr("cena");
        proizvod = $(this).attr("proizvodid");

        $("#popupnaziv").text(naziv);
        $("#popupimg").attr("src", "/photo" + "/" + proizvod + "/" + photo);

        $("#popupcena").text(cena);
        $("#addedToCart-window").css("display", "block");
    });

$(".dodajuKorpu-admin-shop").on("click", function (e) {
        photo = $(this).attr("photoid");
        naziv = $(this).attr("naziv");
        cena = $(this).attr("cena");
        proizvod = $(this).attr("proizvodid");

        $("#popupnaziv").text(naziv);
        $("#popupimg").attr("src", "/photo" + "/" + proizvod + "/" + photo);

        $("#popupcena").text(cena);
        $("#addedToCart-window").css("display", "block");
    });
    
    $(".add-to-cart-with-icon-productAdminPage").on("click", function (e) {
        photo = $(this).attr("photoid");
        naziv = $(this).attr("naziv");
        cena = $(this).attr("cena");
        proizvod = $(this).attr("proizvodid");

        $("#popupnaziv").text(naziv);
        $("#popupimg").attr("src", "/photo" + "/" + proizvod + "/" + photo);

        $("#popupcena").text(cena);
        $("#addedToCart-window").css("display", "block");
    });

*/
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


    //alert za uspesnu porudzbinu da nestane nakon nekoliko sekundi 
    setTimeout(function () {
        $("#alertHvalaPorudzbina").fadeOut(400);
    }, 3500);
    
    
   



///*klik na dugme nazad na popupu sa dodatim proizvodom da se zatvori popup proizvoda koji je dodat u korpu*/
//var modal2 = document.getElementById('nazad-na-shop-btn');
//window.onclick = function (event) {
//    if (event.target === modal2) {
//        modal.style.display = "none";
//    }
//    ;
//};


//jos ne radi
    $(".alert-order").on("click", function () {
        $("#alertHvalaPorudzbina").fadeOut();
    });

//radi
    $("#nazadBTN").on("click", function () {
        $("#alertHvalaPorudzbina").fadeOut();
    });


//        setTimeout(function () { $("#alertHvalaPorudzbina"); }, 3000);


//
///*klik na dugme nazad na popupu sa dodatim proizvodom da se zatvori popup proizvoda koji je dodat u korpu*/
//var modal2 = document.getElementById('nazad-na-shop-btn');
//window.onclick = function (event) {
//    if (event.target === modal2) {
//        modal.style.display = "none";
//    }
//    ;
//};


});

/*
 @GetMapping("/korpa/dodajProizvod/{proizvod_id}")
 @GetMapping("/korpa/dodajProizvodQty/{proizvod_id}/{kolicina}")
 @GetMapping("/korpa/promeniKolicinu/{proizvod_id}/{kolicina}")
 @GetMapping("/korpa/povecajKolicinu/{proizvod_id}")
 @GetMapping("/korpa/smanjiKolicinu/{proizvod_id}")
 @GetMapping("/korpa/skloniProizvod/{proizvod_id}")
 */

//shop strana
$(document).ready(function () {
 updateTotals();
// @GetMapping("/korpa/dodajProizvod/{proizvod_id}")
    $(".dodajJS").on("click", function (e) {
       // idProizvoda = $(this).attr("pid");
        addToCart($(this));
    });
//proizvod strana
// @GetMapping("/korpa/dodajProizvodQty/{proizvod_id}/{kolicina}")
    $(".dodajKolicinuJS").on("click", function (e) {
       // idProizvoda = $(this).attr("pid");
        kolicina = $("#kolicina").val();
        dodajProizvodQty($(this), kolicina);
    });
// sve ostalo korpa strana
// @GetMapping("/korpa/promeniKolicinu/{proizvod_id}/{kolicina}")
    $(".kolicinaJS").on("change", function (e) {
        idProizvoda = $(this).attr("pid");
        kolicina = $(this).val();
        promeniKolicinu(idProizvoda, kolicina);
        updateTotals();
    });
//     @GetMapping("/korpa/povecajKolicinu/{proizvod_id}")
    $(".povecajJS").on("click", function (e) {
        idProizvoda = $(this).attr("pid");
        povecajKolicinu(idProizvoda);
        $('.kolicinaJS[pid="' + idProizvoda + '"]').val(+$('.kolicinaJS[pid="' + idProizvoda + '"]').val() + 1);
        updateTotals();
    });
// @GetMapping("/korpa/smanjiKolicinu/{proizvod_id}")
    $(".smanjiJS").on("click", function (e) {
        idProizvoda = $(this).attr("pid");
        smanjiKolicinu(idProizvoda);
        if ($('.kolicinaJS[pid="' + idProizvoda + '"]').val() > 0) {
            $('.kolicinaJS[pid="' + idProizvoda + '"]').val($('.kolicinaJS[pid="' + idProizvoda + '"]').val() - 1);
        }
        updateTotals();
    });
// @GetMapping("/korpa/skloniProizvod/{proizvod_id}")
    $(".skloni").on("click", function (e) {
        idProizvoda = $(this).attr("pid");
        skloniProizvod(idProizvoda);
        
        
        $('.stavkaJS[pid="' + idProizvoda + '"]').slideUp("slow", function() { $('.stavkaJS[pid="' + idProizvoda + '"]').remove();
 $('.stavkaPregledJS[pid="' + idProizvoda + '"]').remove();
        updateTotals();    
});
       
         
        

        
    });
    $("#predzadnjiKorak").on("click", function (e) {
        $("#zadnjiKorakIme").text($("#predzadnjiKorakIme").val());
        $("#zadnjiKorakPrezime").text($("#predzadnjiKorakPrezime").val());
        $("#zadnjiKorakUlica").text($("#predzadnjiKorakAdresa").val() + ",");
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
    //updateTotals();

});
function updateTotals() {
    $('.totalcenaproizvoda').each(function () {
        pid = $(this).attr("pid");
        cena = parseFloat($('.cenaproizvoda[pid="' + pid + '"]').text());
        kolicina = $('.kolicinaJS[pid="' + pid + '"]').val();
        $(this).text((cena * kolicina).toFixed(2));
    });
    $('.totalkilazaproizvoda').each(function () {
        pid = $(this).attr("pid");
        kila = parseFloat($('.kilazaJS[pid="' + pid + '"]').val());
        kolicina = $('.kolicinaJS[pid="' + pid + '"]').val();
        $(this).text((kila * kolicina).toFixed(2));
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

// @GetMapping("/korpa/dodajProizvod/{proizvod_id}")
//shop strana
function addToCart(element) {
var id = $(element).attr("pid");
  console.log(id);
    var   photo = $(element).attr("photoid");
        console.log(photo);
      var   naziv = $(element).attr("naziv");
      var   cena = $(element).attr("cena");
        
    url = "/korpa/dodajProizvod/" + id;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        cache: false,
        timeout: 600000,
        success: function (response) {
        //   alert(response);
if (response == "Proizvod je već u korpi!"){
     $("#ProizvodDodat").text("Proizvod je već u korpi");
}
        $("#popupnaziv").text(naziv);
        $("#popupimg").attr("src", "/photo" + "/" + id + "/" + photo);

        $("#popupcena").text(cena);
        $("#addedToCart-window").css("display", "block");

        },
        error: function (e) {
            alert(e);
        }
    });
}

// @GetMapping("/korpa/promeniKolicinu/{proizvod_id}/{kolicina}")
function promeniKolicinu(id, kol) {

    url = "/korpa/promeniKolicinu/" + id + "/" + kol;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        cache: false,
        timeout: 600000,
        success: function (response) {
//            alert(response);
        },
        error: function (e) {
//            alert(e);
        }
    });
}


// @GetMapping("/korpa/povecajKolicinu/{proizvod_id}")
function povecajKolicinu(id) {

    url = "/korpa/povecajKolicinu/" + id;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        cache: false,
        timeout: 600000,
        success: function (response) {
//            alert(response);
        },
        error: function (e) {
//            alert(e);
        }
    });
}

// @GetMapping("/korpa/smanjiKolicinu/{proizvod_id}")
function smanjiKolicinu(id) {

    url = "/korpa/smanjiKolicinu/" + id;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        cache: false,
        timeout: 600000,
        success: function (response) {
//            alert(response);
        },
        error: function (e) {
//            alert(e);
        }
    });
}

// @GetMapping("/korpa/skloniProizvod/{proizvod_id}")
function skloniProizvod(id) {

    url = "/korpa/skloniProizvod/" + id;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        cache: false,
        timeout: 600000,
        success: function (response) {
//            alert(response);
        },
        error: function (e) {
//            alert(e);
        }
    });
}

// @GetMapping("/korpa/dodajProizvodQty/{proizvod_id}/{kolicina}")
//proizvod strana
function dodajProizvodQty(element, kol) {
var id = $(element).attr("pid");
  console.log(id);
    var   photo = $(element).attr("photoid");
        console.log(photo);
      var   naziv = $(element).attr("naziv");
      var   cena = $(element).attr("cena");
    url = "/korpa/dodajProizvodQty/" + id + "/" + kol;
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: url,
        cache: false,
        timeout: 600000,
        success: function (response) {
          
        //   alert(response);
if (response == "Proizvod je već u korpi!"){
     $("#ProizvodDodat").text("Proizvod je već u korpi");
}
        $("#popupnaziv").text(naziv);
        $("#popupimg").attr("src", "/photo" + "/" + id + "/" + photo);

        $("#popupcena").text(cena);
        $("#addedToCart-window").css("display", "block");

        },
        error: function (e) {
            alert(e);
        }
    });
}






/*minus i plus dugme na proizvod strani*/
jQuery(document).ready(function () {
// This button will increment the value
    $(".plus").click(function (e) {

        e.preventDefault();
        // Define field variable
        field = "input[name=" + $(this).attr("field") + "]";
        // Get its current value
        var currentVal = parseInt($(field).val());
        // If is not undefined
        if (!isNaN(currentVal) && currentVal < 20) {

// Increment
            $(field).val(currentVal + 1);
        }

    });
// This button will decrement the value till 0
    $(".minus").click(function (e) {

        e.preventDefault();
        // Define field variable
        field = "input[name=" + $(this).attr("field") + "]";
        // Get its current value
        var currentVal = parseInt($(field).val());
        // If it isn't undefined or its greater than 0
        if (!isNaN(currentVal) && currentVal > 1) {
// Decrement one
            $(field).val(currentVal - 1);
        }

    });


});
/*minus i plus dugme na proizvod strani*/







//        /*popup*/
//       $(document).ready(function ($) {
//$(".buttonAdd2Cart").on("click", function (event) {
//event.preventDefault();
//       $("#addedToCart-window").addClass("active");
//       setTimeout(function () {
//            $(".submit-window").removeClass("active");
//        }, 5000);
//});


/*
 dodavanje u korpuy popup vindow treba srediti
 $(".popup").on("click", function () {
 var id = $(this).attr("pid");
 $('.addedToCart-window['+id']').css("display","block");
 };
 */





