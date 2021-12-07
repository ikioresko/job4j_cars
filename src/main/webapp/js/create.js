function validate() {
    var x = Boolean(true);
    if ($('#category').val() === "Select") {
        alert($('#category').attr('title'));
        x = false;
    }
    if ($('#brand').val() === "0") {
        alert($('#brand').attr('title'));
        x = false;
    }
    if ($('#model').val() === "Select") {
        alert($('#model').attr('title'));
        x = false;
    }
    if ($('#body').val() === "Select") {
        alert($('#body').attr('title'));
        x = false;
    }
    if ($('#description').val() === "") {
        alert($('#description').attr('title'));
        x = false;
    }
    if ($('#price').val() === "" || $('#price').val() < 0) {
        alert($('#price').attr('title'));
        x = false;
    }
    return x;
}

function setModelList(x) {
    model(x)
}

$(document).ready(function () {
    brand();
    body();
    category();
});

function brand() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/brand',
        dataType: 'json'
    }).done(function (data) {
        for (var brand of data) {
            $('#brand').append($('<option>', {
                value: brand.id,
                text: brand.name
            }));
        }
    }).fail(function (err) {
        console.log(err);
    });
}

function body() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/body',
        dataType: 'json'
    }).done(function (data) {
        for (var body of data) {
            $('#body').append($('<option>', {
                value: body.id,
                text: body.name
            }));
        }
    }).fail(function (err) {
        console.log(err);
    });
}

function category() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/category',
        dataType: 'json'
    }).done(function (data) {
        for (var category of data) {
            $('#category').append($('<option>', {
                value: category.id,
                text: category.name
            }));
        }
    }).fail(function (err) {
        console.log(err);
    });
}

function model(x) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/cars/model',
        data: {"brandId": x},
        dataType: 'json'
    }).done(function (data) {
        $('#model').empty();
        for (var model of data) {
            $('#model').append($('<option>', {
                value: model.id,
                text: model.name
            }));
        }
    }).fail(function (err) {
        console.log(err);
    });
}