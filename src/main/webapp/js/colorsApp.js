angular.module('colorsApp', []).controller('colorsCtrl', ['$scope', '$http', function($scope, $http) {
    $scope.squares = [];
    $scope.usage = '';

    /*
     * send POST to /codechallenge/colors
     * payload is string in input
     */
    $scope.submitChars = function(){
        $http({
            method: 'POST',
            url: 'http://localhost:8080/codechallenge/colors',
            data: $scope.characters,
            headers: {'Content-Type': 'text/plain'}
        }).then(function successCallback(response) {
                $scope.usage = '';
                $scope.squares = response.data;
                $scope.$apply();
            }, function errorCallback(response) {
                alert('invalid input!');
            });
    }

    /*
     * send POST to /codechallenge/characters
     * payload is first char in input
     */
    $scope.getUsage = function(){
        $scope.submitted = $scope.characters[0];
        $scope.characters = [$scope.submitted];
        $scope.submitted = $scope.submitted || 'space';

        $http({
            method: 'POST',
            url: 'http://localhost:8080/codechallenge/characters',
            data: $scope.submitted,
            headers: {'Content-Type': 'text/plain'}
        }).then(function successCallback(response) {
                $scope.squares = [];
                $scope.usage = response.data;
                $scope.$apply();
            }, function errorCallback(response) {
                alert('invalid input!');
            });
        }
}]).directive('colorSquare', function() {
    return {
        templateUrl: '../colorsquare.tpl.html', // using a template file because I don't like HTML in my JS files
    };
})