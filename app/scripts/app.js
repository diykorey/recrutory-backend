'use strict';

/**
 * @ngdoc overview
 * @name kandidatoApp
 * @description
 * # kandidatoApp
 *
 * Main module of the application.
 */


var kandidatoApp = angular  .module('kandidatoApp', [
    'ngResource',
    'ngRoute',
    'ngTouch',
    'ngMaterial',
    'ngAnimate'
  ])


kandidatoApp.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('default')
    .primaryPalette('blue-grey')
    .accentPalette('light-blue');
});


  kandidatoApp.config(function ($routeProvider) {
    $routeProvider
      .when('/dashboard', {
        templateUrl: 'views/dashboard.html',
        controller: 'AboutCtrl'
      })
      .when('/list', {
        templateUrl: 'views/list.html',
        controller: 'AboutCtrl'
      })
      .when('/candidateDashboard', {
        templateUrl: 'views/candidateDashboard.html',
        controller: 'candidateDash'
      })
      .when('/candidateList', {
        templateUrl: 'views/candidateList.html',
        controller: 'AboutCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });


kandidatoApp.$inject = ['$scope', 'ApiDataFactory'];



kandidatoApp.factory('ApiDataFactory', ['$http', '$q', function($http, $q) {

    var factory = {
        query: function(url) {
            var URL = url;
            var data = $http({
                method: 'GET',
                url: URL
            }).then(function(result) {
                console.log("Data got");
                return result.data

            }, function(result) {
                console.log("Error: No data returned, probably API Issues");
            });

            return data;
        }
    }
    return factory;
}]);



