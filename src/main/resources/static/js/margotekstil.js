
// navbar background color change on scroll
$(function () {
    //meni da promeni boju kad se skroluje
    $(document).scroll(function () {
        var $nav = $(".fixed-top");
        if ($(this).scrollTop() > 0) {
            $nav.toggleClass('scrolled', true);
            $("#brandlogo").attr("src", "/img/logobeli.png")
        } else {
            $nav.toggleClass('scrolled', false);
            $("#brandlogo").attr("src", "/img/logocrveni.png")

        }
    });
});
// end of navbar background color change on scroll


jQuery(document).ready(function () {
    
    /*back to top button*/
//   insert back to top button dynamicly
    /*stacked font awesome icons*/
//    $("#backToTop").append('<a class="backToTop" href="javascript:void(null);" style="display: none;"><span class="fa-stack fa-lg">  <i class="fa fa-circle fa-stack-2x"></i><i class="fa fa-angle-up fa-stack-1x"></i></span></a>');
    $("#backToTop").append('<a class="backToTop" style="display:none;"><img class="JSb2t" src="/img/backToTopRedish.png" alt="back to top button"></a>');

    var $window = $(window);
    var distance = 80;
/*end of back to top button*/




    //ako je refreshovana stranica pa je vec skrolovana da se odmah pojavi back to top i meni promeni boju
    if ($window.scrollTop() >= distance) {
        $(".backToTop").fadeIn();
        var $nav = $(".fixed-top");
        $nav.toggleClass('scrolled', true);
        $("#brandlogo").attr("src", "/img/logobeli.png")
    }
    ;

    //alert za uspesnu prijavu/login da nestane nakon nekoliko sekundi 
    setTimeout(function () {
        $("#alertUspesnaPrijava").fadeOut(400);
    }, 3500);


    // scroll
    $window.scroll(function () {
        // header
        if ($window.scrollTop() >= distance) {
            $(".backToTop").fadeIn();
        } else {
            $(".backToTop").fadeOut();
        }
    });

    $('.backToTop').click(function () {
        $('html, body').animate({
            scrollTop: 0
        }, 800);
    });

//    $(".JSb2t").mouseover(function () {
//        this.src = "/img/backToTopRedish.png";
//    }).mouseout(function () {
//        this.src = "/img/backToTopBlack.png";
//    });


    /*hamnurger meni da se zatvori kad se klikne bilo gde - radi i ovaj ALI
         oba se suzava kad se klikne */
    $(function () {
        $(document).click(function (event) {
            $('.navbar-collapse').collapse('hide');
        });
    });
    
        /*hamnurger meni da se zatvori kad se klikne bilo gde - radi i ovaj ALI
         oba se suzava kad se klikne */
//    $(document).click(
//        function (event) {
//            var target = $(event.target);
//            var _mobileMenuOpen = $(".navbar-collapse").hasClass("show");
//            if (_mobileMenuOpen === true && !target.hasClass("navbar-toggler")) {
//                $("button.navbar-toggler").click();
//            }
//        }
//    );



//Highlight current page navigation menu item
$(function() {
  $('#nav a[href^="/' + location.pathname.split("/")[1] + '"]').addClass('currentNavItem');
});




});
















//
///* products page-plus,minus, add to cart buttons*/
//
///*plus minus button*/
//jQuery(document).ready(function () {
//// This button will increment the value
//    $(".plus").click(function (e) {
//        e.preventDefault();
//        // Define field variable
//        field = "input[name=" + $(this).attr("field") + "]";
//        // Get its current value
//        var currentVal = parseInt($(field).val());
//        // If is not undefined
//        if (!isNaN(currentVal) && currentVal < 20) {
//            // Increment
//            $(field).val(currentVal + 1);
//
//        }
//
//    });
//
//// This button will decrement the value till 0
//    $(".minus").click(function (e) {
//
//        e.preventDefault();
//
//        // Define field variable
//        field = "input[name=" + $(this).attr("field") + "]";
//
//        // Get its current value
//        var currentVal = parseInt($(field).val());
//
//        // If it isn't undefined or its greater than 0
//        if (!isNaN(currentVal) && currentVal > 1) {
//            // Decrement one
//            $(field).val(currentVal - 1);
//        }
//    });
//});
///*end of plus minus button*/
//
//
///*add to cart button*/
//$(document).ready(function () {
//    $(".add-to-cart").click(function () {
//        if ($(this).hasClass("in-cart")) {
//            $(this).removeClass("in-cart").text("Dodaj u korpu ").append('<i class="fa fa-shopping-cart"></i>');
//            ;
//        } else {
//            var btn = $(this);
//            btn.addClass("anim").text("Dodavanje...");
//
//            setTimeout(function () {
//                btn.removeClass("anim").addClass("in-cart").text("Dodato ").append('<i class="fa fa-check-circle"></i>');
//            }, 2000);
//        }
//    });
//});
///*end of add to cart button*/
//





//
//
//
//
//
//$(window).resize(function () {
//    if ($(window).width() > 765) {
//        $('.swirl-img').removeClass('swirl-img');
//    }
//}
//);











//
//
//
//var check = false;
//
//function changeVal(el) {
//    var qt = parseFloat(el.parent().children(".qt").html());
//    var price = parseFloat(el.parent().children(".price").html());
//    var eq = Math.round(price * qt * 100) / 100;
//
//    el.parent().children(".full-price").html(eq + " RSD");
//
//    changeTotal();
//}
//
//function changeTotal() {
//
//    var price = 0;
//
//    $(".full-price").each(function (index) {
//        price += parseFloat($(".full-price").eq(index).html());
//    });
//
//    price = Math.round(price * 100) / 100;
//    var tax = Math.round(price * 0.05 * 100) / 100
//    var shipping = parseFloat($(".shipping span").html());
//    var fullPrice = Math.round((price + tax + shipping) * 100) / 100;
//
//    if (price === 0) {
//        fullPrice = 0;
//    }
//
//    $(".subtotal span").html(price);
//    $(".tax span").html(tax);
//    $(".total span").html(fullPrice);
//}
//
//$(document).ready(function () {
//
//    $(".remove").click(function () {
//        var el = $(this);
//        el.parent().parent().addClass("removed");
//        window.setTimeout(
//                function () {
//                    el.parent().parent().slideUp('fast', function () {
//                        el.parent().parent().remove();
//                        if ($(".product").length === 0) {
//                            if (check) {
//                                $("#cart").html("<h1>Prodavnica još uvek nije u funkciji!</h1>");
//                            } else {
//                                $("#cart").html("<h1>Nema proizvoda!</h1>");
//                            }
//                        }
//                        changeTotal();
//                    });
//                }, 200);
//    });
//
//    $(".qt-plus").click(function () {
//        $(this).parent().children(".qt").html(parseInt($(this).parent().children(".qt").html()) + 1);
//
//        $(this).parent().children(".full-price").addClass("added");
//
//        var el = $(this);
//        window.setTimeout(function () {
//            el.parent().children(".full-price").removeClass("added");
//            changeVal(el);
//        }, 150);
//    });
//
//    $(".qt-minus").click(function () {
//
//        child = $(this).parent().children(".qt");
//
//        if (parseInt(child.html()) > 1) {
//            child.html(parseInt(child.html()) - 1);
//        }
//
//        $(this).parent().children(".full-price").addClass("minused");
//
//        var el = $(this);
//        window.setTimeout(function () {
//            el.parent().children(".full-price").removeClass("minused");
//            changeVal(el);
//        }, 150);
//    });
//
//    
//
//    $(".btn").click(function () {
//        check = true;
//        $(".remove").click();
//    });
//});
//




//
//
///*quantity control*/
//$(document).ready(function () {
//    $(".minusButton").on("click", function (e) {
//        e.preventDefault();
//        productId = $(this).attr("prId");
//        qtyInput = $("#kolicina" + id);
//
//        newQty = parseInt(qtyInput.val()) - 1;
//        if (newQty > 0)
//            qtyInput.val(newQty);
//    });
//
//    $(".plusButton").on("click", function (e) {
//        e.preventDefault();
//        productId = $(this).attr("prId");
//        qtyInput = $("#kolicina" + id);
//
//        newQty = parseInt(qtyInput.val()) + 1;
//        if (newQty < 11)
//            qtyInput.val(newQty);
//    });
//});
//
//
//
//






$(document).ready(function () {
    $('[data-toggle="popover"]').popover({
        placement: 'bottom',
        trigger: 'hover'
    });
});


