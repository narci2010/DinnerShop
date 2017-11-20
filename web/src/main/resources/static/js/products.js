/**
 * Created by Tomek on 08-Feb-17.
 */
function Products($scope, $http) {
    $http.get('/products.json').success(function (data) {
        $scope.products = data;
    });

};

angular.module('app', [])
    .directive('updateCheckboxDirective', function() {
        return function(scope, element, attrs) {
            angular.element(element).css('color','blue');

        };
    });



