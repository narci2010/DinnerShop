/**
 * Created by Tomek on 08-Feb-17.
 */
function Products($scope, $http) {
    $http.get('/products.json').success(function (data) {
        $scope.products = data;
    })};

/*
var app = angular.module('app', []);
*/

/*angular.module('app')
 .factory('XSRFInterceptor', function ($cookies, $log) {

 var XSRFInterceptor = {

 request: function(config) {

 var token = $cookies.get('XSRF-TOKEN');

 if (token) {
 config.headers['X-XSRF-TOKEN'] = token;
 $log.info("X-XSRF-TOKEN: " + token);
 }

 return config;
 }
 };
 return XSRFInterceptor;
 });

 angular.module('app', [])
 .config(['$httpProvider', function ($httpProvider) {

 $httpProvider.defaults.withCredentials = true;
 $httpProvider.interceptors.push('XSRFInterceptor');

 }]);*/
/*
app.factory('ShoppingCartService', function ($http) {

    var getData = function () {
        return $http({method: "GET", url: '/shoppingCart.json'}).then(function (cart) {

            return cart.data;
        });
    };

    function update(productId, url) {
        console.log('id ' + productId + ' url : ' + url);
        var dataToSend = $.param({productId: productId});

        $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'},
            method: "POST",
            data: dataToSend,
            url: url

        });
    }

    return {
        getData: getData,
        update: update
    };
});

app.factory('ProductService', function ($http) {

    var changed;
    var changedId = 0;

    var getData = function () {

        return $http({method: "GET", url: '/products.json'}).then(function (products) {

            return products.data;
        });
    };

    function setChange(change) {
        changed = change;
    }

    function getChange() {
        return changed;
    }

    function setChangedId(id) {
        changedId = id;
    }

    function getChangedId() {
        return changedId;
    }

    return {
        getData: getData,
        setChange: setChange,
        getChange: getChange,
        setChangedId: setChangedId,
        getChangedId: getChangedId
    };
});


function ShoppingCart($scope, ShoppingCartService, ProductService) {
    var data = ShoppingCartService.getData();
    data.then(function (cart) {

        $scope.shoppingCart = cart;
        console.log(cart);
    });


    $scope.$watch(function () {
            return ProductService.getChange();
        },
        function (value) {
            if (value == true) {
                ShoppingCartService.update(ProductService.getChangedId(), '/addToCart');
            }
            else if (value == false) {
                ShoppingCartService.update(ProductService.getChangedId(), '/removeItem');
            }

            var data = ShoppingCartService.getData();
            data.then(function (cart) {

                $scope.shoppingCart = cart;
                console.log(cart);
            });
        });


}

function Products($scope, ProductService) {
    var data = ProductService.getData();
    data.then(function (products) {

        $scope.products = products;
        console.log(products);
    });

    $scope.change = function (event, id) {
        ProductService.setChange(event.target.checked);
        ProductService.setChangedId(id);
    }
}
*/

/*
 var app = angular.module('app', []);

 app.factory("Service", function ($http) {
 // var data;


 function getData() {
 $http.get('/shoppingCart.json').then(function (result) {
 console.log('I am sending message for shopping cart');
 // data = cart;
 return result;
 });

 }

 function setData(newData) {
 // data = newData;
 }

 return {
 getData: getData,
 setData: setData,
 }
 });


 function ShoppingCart($scope, $http, Service) {

 // console.log('Service get data : ' + Service.getData());
 Service.getData().then(function (result) {
 $scope.data = result;
 console.log("data.name"+$scope.data.name);
 });


 }


 */


/*
 function Products($scope, $http) {
 function addCheckSignatureToCheckbox(cart) {
 $(cart).each(function () {
 $('#productsTableBody').find('input#' + this.id).attr("checked", "checked").parents('tr').addClass('selected');
 });
 }

 $scope.refresh = function () {
 $http.get('/shoppingCart.json').success(function (cart) {
 $scope.shoppingCart = cart;
 });
 };

 $http.get('/products.json', {cache: true})
 .success(function (data) {
 $scope.products = data;

 $http.get('/shoppingCart.json').success(function (cart) {
 $scope.shoppingCart = cart;
 addCheckSignatureToCheckbox(cart);

 });


 });

 $scope.refresh();
 }*/
