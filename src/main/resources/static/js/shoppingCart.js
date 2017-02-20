/**
 * Created by Tomek on 16-Feb-17.
 */

function shoppingCart(callback) {
    $.get("/shoppingCart").success(function (shoppingCartProducts) {
        callback(shoppingCartProducts);
    });
};

function updateNavbarBasketItems(shoppingCartProducts) {
    $('#shoppingCartProducts').empty();
    $('#shoppingCartProducts').append("<a href='#a'><i class='fa fa-shopping-cart '> " + shoppingCartProducts.length + " ITEM(S)</i></a>")
}

function updateNavbarUserMoney(money) {
    $('#userMoney').empty();
    $('#userMoney').append("<a href='#a'>" + money + "<i class='fa fa-usd'>  </i></a>")
}

//Test for now!!!
function userMoney(callback) {
    $.get("/userMoney").success(function (data) {
        console.log('user have money : ' + data);
        callback(data);

    });
};


function updateTable(shoppingCartProducts) {
    $('#shoppingCartTable').empty();

    $.each(shoppingCartProducts, function (index, product) {
        $('#shoppingCartTable').append("<tr> <td>" + product.id + "</td>" +
            "<td>" + product.description + "</td>" +
            "<td> <img class='img-responsive image-size' src='data:image/JPEG;base64," + product.image + "'> </td> </tr>");
    });
}

function addCheckSignatureToCheckbox(cart) {
    $(cart).each(function () {
        $('#productsTableBody').find('input#' + this.id).attr("checked", "checked").parents('tr').addClass('selected');
    });
}

function clearAppropriateCheckbox(id) {
    $('#productsTableBody').find('input#' + id).prop("checked", false).parents('tr').toggleClass('selected');

}

function editShoppingCart(id, url) {
    $.ajax({
        type: "POST",
        data: {"productId": id},
        url: url,
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(textStatus);
        },
        success: function (data) {
            console.log('response for message : ' + url + ' equals : ' + data);
            if (data == true) {
                shoppingCart(updateTable);
            }
            else if (data == false) {
                clearAppropriateCheckbox(id);
                BootstrapDialog.show({
                    message: 'Sorry, you do not have enough money from this product',
                    type: BootstrapDialog.TYPE_WARNING
                });

            }
            shoppingCart(updateNavbarBasketItems);
            userMoney(updateNavbarUserMoney);

        }
    });
}