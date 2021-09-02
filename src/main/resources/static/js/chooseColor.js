
jQuery(document).ready(function () {

//    choose color for product
    $('.radio-group .radio').click(function () {
        $(this).parent().parent().find('.radio').removeClass('selected');
        $(this).addClass('selected');
    });

});




