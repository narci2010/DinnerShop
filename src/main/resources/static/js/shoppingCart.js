/**
 * Created by Tomek on 16-Feb-17.
 */

function userMoney(callback) {
    $.get("/user/money").success(function (data) {
        callback(data);
    });

}
function updateNavbarUserMoney(money) {
    $('#userMoney').empty();
    $('#userMoney').append("<a href='#a'>" + money.denomination + " " + money.currencyCode + "</a>")
}




