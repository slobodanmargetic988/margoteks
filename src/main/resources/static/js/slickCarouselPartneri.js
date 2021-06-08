$(document).ready(function () {
    $('.customer-logos').slick({
       
        slidesToShow: 5,
        slidesToScroll: 2,
        autoplay: true,
        autoplaySpeed: 3000,
        arrows: false,
        dots: false,
        pauseOnHover: false,
        responsive: [{
                breakpoint: 767,
                settings: {
                    slidesToShow: 4
                }
            }, {
                breakpoint: 520,
                settings: {
                    slidesToShow: 3
                }
            }]
    });
      $('.slick-track').attr('aria-label', 'Our customers logos');
             
              
});