$(document).ready(function () {
    $("#addressBookCreate").hide();
    $("#addBuddyResult").hide();
    $("#addressBookView").hide();

    $("#create-form").submit(function (event) {
        event.preventDefault();

        var formData = {};

        $.ajax({
            type: "POST",
            url: "/addressBooks",
            data: formData,
            dataType: "json",
        }).then(function (data) {
            console.log(data);
            $("#addressBookCreate").show();
        });
    });

    $("#add-form").submit(function (event) {
        event.preventDefault();

        var formData = {
            name: $("#add-name").val(),
            phoneNumber: $("#add-number").val(),
        };

        $.ajax({
            type: "PATCH",
            url: "/addressBooks/" + $("#add-id").val() + "/buddies",
            data: formData,
            dataType: "json",
        }).then(function (data) {
            console.log(data);
            $("#addBuddyResult").show();
        });
    });

    $("#view-form").submit(function (event) {
        event.preventDefault();

        $.ajax({
            type: "GET",
            url: "/addressBooks/" + $("#view-id").val(),
        }).then(function (data) {
            console.log(data);
            $("#addressBookView").show();
        });
    });
});