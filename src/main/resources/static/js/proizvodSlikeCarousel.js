

$(".show-small-img:first-of-type").css({
    border: "solid 1px #951b25",
    padding: "2px"
});
$(".show-small-img:first-of-type")
        .attr("walt", "now")
        .siblings()
        .removeAttr("walt");
$(".show-small-img").click(function () {
    $("#show-img").attr("src", $(this).attr("src"));
    $("#show-img-link").attr("href", $(this).attr("src"));

    $("#big-img").attr("src", $(this).attr("src"));
    $(this).attr("walt", "now").siblings().removeAttr("walt");
    $(this)
            .css({border: "solid 1px #951b25", padding: "2px"})
            .siblings()
            .css({border: "none", padding: "0"});
    if ($("#small-img-roll").children().length > 4) {
        if (
                $(this).index() >= 3 &&
                $(this).index() < $("#small-img-roll").children().length - 1
                ) {
            $("#small-img-roll").css("left", -($(this).index() - 2) * 76 + "px");
        } else if ($(this).index() == $("#small-img-roll").children().length - 1) {
            $("#small-img-roll").css(
                    "left",
                    -($("#small-img-roll").children().length - 4) * 76 + "px"
                    );
        } else {
            $("#small-img-roll").css("left", "0");
        }
    }
});
// Click '>' to next
$("#next-img").click(function () {
    $("#show-img").attr(
            "src",
            $(".show-small-img[walt='now']").next().attr("src")
            );
    $("#show-img-link").attr(
            "href",
            $(".show-small-img[walt='now']").next().attr("src")
            );

    $("#big-img").attr("src", $(".show-small-img[walt='now']").next().attr("src"));
    $(".show-small-img[walt='now']")
            .next()
            .css({border: "solid 1px #951b25", padding: "2px"})
            .siblings()
            .css({border: "none", padding: "0"});
    $(".show-small-img[walt='now']")
            .next()
            .attr("walt", "now")
            .siblings()
            .removeAttr("walt");
    if ($("#small-img-roll").children().length > 4) {
        if (
                $(".show-small-img[walt='now']").index() >= 3 &&
                $(".show-small-img[walt='now']").index() <
                $("#small-img-roll").children().length - 1
                ) {
            $("#small-img-roll").css(
                    "left",
                    -($(".show-small-img[walt='now']").index() - 2) * 76 + "px"
                    );
        } else if (
                $(".show-small-img[walt='now']").index() ==
                $("#small-img-roll").children().length - 1
                ) {
            $("#small-img-roll").css(
                    "left",
                    -($("#small-img-roll").children().length - 4) * 76 + "px"
                    );
        } else {
            $("#small-img-roll").css("left", "0");
        }
    }
});
// Click'<' to previous
$("#prev-img").click(function () {
    $("#show-img").attr(
            "src",
            $(".show-small-img[walt='now']").prev().attr("src")
            );
    $("#show-img-link").attr(
            "href",
            $(".show-small-img[walt='now']").prev().attr("src")
            );


    $("#big-img").attr("src", $(".show-small-img[walt='now']").prev().attr("src"));
    $(".show-small-img[walt='now']")
            .prev()
            .css({border: "solid 1px #951b25", padding: "2px"})
            .siblings()
            .css({border: "none", padding: "0"});
    $(".show-small-img[walt='now']")
            .prev()
            .attr("walt", "now")
            .siblings()
            .removeAttr("walt");
    if ($("#small-img-roll").children().length > 4) {
        if (
                $(".show-small-img[walt='now']").index() >= 3 &&
                $(".show-small-img[walt='now']").index() <
                $("#small-img-roll").children().length - 1
                ) {
            $("#small-img-roll").css(
                    "left",
                    -($(".show-small-img[walt='now']").index() - 2) * 76 + "px"
                    );
        } else if (
                $(".show-small-img[walt='now']").index() ==
                $("#small-img-roll").children().length - 1
                ) {
            $("#small-img-roll").css(
                    "left",
                    -($("#small-img-roll").children().length - 4) * 76 + "px"
                    );
        } else {
            $("#small-img-roll").css("left", "0");
        }
    }
});
