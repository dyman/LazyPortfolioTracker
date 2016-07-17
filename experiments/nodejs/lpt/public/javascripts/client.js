var app = angular.module('portfolio', []);

app.controller('MainCtrl', [
'$scope',
function($scope){
  $scope.test = 'Hello world!';
  $scope.name = 'mandy peter'
}]);