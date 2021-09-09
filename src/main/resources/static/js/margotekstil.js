
$(".navbar-toggler").click(function () {
    $(".navbar").toggleClass("navbar-burgundy");
});

$(function () {
    //meni da promeni boju kad se skroluje
    $(document).scroll(function () {
        var $nav = $(".fixed-top");
        if ($(this).scrollTop() > 0) {
            $nav.toggleClass('scrolled', true);
            $("#brandlogo").attr("src", "/img/logobeli.png");
        } else {
            $nav.toggleClass('scrolled', false);
            $("#brandlogo").attr("src", "/img/logocrveni.png");

        }
    });
});
// end of navbar background color change on scroll



jQuery(document).ready(function () {
//    $("#backToTop").append('<a class="backToTop" style="display:none;"><img class="JSb2t" src="/img/backToTopBtnGreen.png" alt="back to top button"></a>');
//    var $window = $(window);
//    var distance = 80;
//    if ($window.scrollTop() >= distance) {
//        $(".backToTop").fadeIn();
//        var $nav = $(".fixed-top");
//        $nav.toggleClass('scrolled', true);
//        $("#brandlogo").attr("src", "/img/logobeli.png")
//    }
//    ;

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

    $(function () {
        $(document).click(function (event) {
            $('.navbar-collapse').collapse('hide');

        });
    });

//Highlight current page navigation menu item
    $(function () {
        if (location.pathname === "/") {
            $('#homeLink').addClass('currentNavItem');
        } else {
            if (location.pathname.split("/")[1] == "admin") {
                $('#nav a[href^="/admin/' + location.pathname.split("/")[2] + '"]').addClass('currentNavItem');
            } else {
                $('#nav a[href^="/' + location.pathname.split("/")[1] + '"]').addClass('currentNavItem');
            }
        }
    });



});


//tooltip na navbar-u
$(document).ready(function () {
    $('[data-toggle="popover"]').popover({
        placement: 'bottom',
        trigger: 'hover'
    });
});



