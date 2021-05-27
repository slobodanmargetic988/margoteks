function initTricks() {
    var labels = $('.floating-placeholder label');
    labels.each(function (i) {
        var ph = $(labels[i])
                .siblings('input')
                .first()
                .attr('placeholder');
        $(labels[i]).html(ph);
    });
}

$(document).ready(function () {
    $('.floating-placeholder input').keyup(function () {
        var input = $(this).val();
        if (input)
            $(this).parent().addClass('float');
        else
            $(this).parent().removeClass('float');
    });

    $('#form').submit(function (e) {
        e.preventDefault();
    });

    initTricks();
});






function initTricks2() {
    var labels = $('.floating-placeholder-textarea label');
    labels.each(function (i) {
        var ph = $(labels[i])
                .siblings('textarea')
                .first()
                .attr('placeholder');
        $(labels[i]).html(ph);
    });
}

$(document).ready(function () {
    $('.floating-placeholder-textarea textarea').keyup(function () {
        var textarea = $(this).val();
        if (textarea)
            $(this).parent().addClass('float');
        else
            $(this).parent().removeClass('float');
    });

    $('#form').submit(function (e) {
        e.preventDefault();
    });

    initTricks2();
});
