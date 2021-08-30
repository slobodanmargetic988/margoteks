

// Lightbox
var $overlay = $('<div id="overlay-proizvod-margo"></div>');
var $image = $("<img>");
var $prevButton = $('<div id="previous-button"><i class="fa fa-chevron-left"></i></div>');
var $nextButton = $('<div id="next-button"><i class="fa fa-chevron-right"></i></div>');
var $exitButton = $('<div id="exit-button"><i class="fa fa-times"></i></div>');

// Add overlay
$overlay.append($image).prepend($prevButton).append($nextButton).append($exitButton);
$("#prikaz-showing").append($overlay);

// Hide overlay on default
$overlay.hide();

// When an image is clicked
$(".img-overlay-margo").click(function (event) {
    // Prevents default behavior
    event.preventDefault();
    // Adds href attribute to variable
    var imageLocation = $(this).prev().attr("href");
    // Add the image src to $image
    $image.attr("src", imageLocation);
    // Fade in the overlay
    $overlay.fadeIn("slow");
});

// When the overlay is clicked
$overlay.click(function () {
    // Fade out the overlay
    $(this).fadeOut("slow");
});

// When next button is clicked
$nextButton.click(function (event) {
    // Hide the current image
    $("#overlay-proizvod-margo img").hide();
    // Overlay image location
    var $currentImgSrc = $("#overlay-proizvod-margo img").attr("src");
    // Image with matching location of the overlay image

    var $currentImg = $('#prikaz-showing img[src="' + $currentImgSrc + '"]');
    // Finds the next image
    var $nextImg = $($currentImg.closest(".sledece").next());


    // All of the images in the gallery
    var $images = $("#prikaz-showing img");
    // If there is a next image
    if ($nextImg.length > 0) {
        // Fade in the next image
        $("#overlay-proizvod-margo img").attr("src", $nextImg.attr("src")).fadeIn(800);
    } else {
        // Otherwise fade in the first image
        $("#overlay-proizvod-margo img").attr("src", $($images[0]).attr("src")).fadeIn(800);
    }
    // Prevents overlay from being hidden
    event.stopPropagation();
});

// When previous button is clicked
$prevButton.click(function (event) {
    // Hide the current image
    $("#overlay-proizvod-margo img").hide();
    // Overlay image location
    var $currentImgSrc = $("#overlay-proizvod-margo img").attr("src");
    // Image with matching location of the overlay image

    var $currentImg = $('#prikaz-showing img[src="' + $currentImgSrc + '"]');
    // Finds the next image
    var $nextImg = $($currentImg.closest(".sledece").prev());


    // All of the images in the gallery
    var $images = $("#prikaz-showing img");
    // If there is a next image
    if ($nextImg.length > 0) {
        // Fade in the next image
        $("#overlay-proizvod-margo img").attr("src", $nextImg.attr("src")).fadeIn(800);
    } else {
        // Otherwise fade in the first image
        $("#overlay-proizvod-margo img").attr("src", $($images[0]).attr("src")).fadeIn(800);
    }
    // Prevents overlay from being hidden
    event.stopPropagation();
});

// When the exit button is clicked
$exitButton.click(function () {
    // Fade out the overlay
    $("#overlay-proizvod-margo").fadeOut("slow");
});

