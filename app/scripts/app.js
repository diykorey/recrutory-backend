'use strict';

/**
 * @ngdoc overview
 * @name kandidatoApp
 * @description
 * # kandidatoApp
 *
 * Main module of the application.
 */


var kandidatoApp = angular.module('kandidatoApp', [
    'ngResource',
    'ngRoute',
    'ngTouch',
    'ngMaterial',
    'ngAnimate'
])


kandidatoApp.config(function($mdThemingProvider) {
  $mdThemingProvider.definePalette('redTheme', {
    '50': 'f15f4c',
    '100': 'f15f4c',
    '200': 'f15f4c',
    '300': 'f15f4c',
    '400': 'f15f4c',
    '500': 'f15f4c',
    '600': 'f15f4c',
    '700': 'f15f4c',
    '800': 'f15f4c',
    '900': 'f15f4c',
    'A100': 'f15f4c',
    'A200': 'f15f4c',
    'A400': 'f15f4c',
    'A700': 'f15f4c'
  });
   $mdThemingProvider.definePalette('grayTheme', {
    '50': '4b494f',
    '100': '4b494f',
    '200': '4b494f',
    '300': '4b494f',
    '400': '4b494f',
    '500': '4b494f',
    '600': '4b494f',
    '700': '4b494f',
    '800': '4b494f',
    '900': '4b494f',
    'A100': '4b494f',
    'A200': '4b494f',
    'A400': '4b494f',
    'A700': '4b494f'
  });
  $mdThemingProvider.theme('default')
    .primaryPalette('redTheme')
    $mdThemingProvider.theme('default')
    .accentPalette('grayTheme')
});


// #f15f4c
// #4b494f

kandidatoApp.config(function($routeProvider) {
    $routeProvider
        .when('/dashboard', {
            templateUrl: 'views/dashboard.html',
            controller: 'dashCtrl'
        })
        .when('/list', {
            templateUrl: 'views/list.html',
            controller: 'listCtrl'
        })
        .when('/addVacancy', {
            templateUrl: 'views/addVacancy.html',
            controller: 'addVacancyCtrl',
        })
        .when('/addCandidate', {
            templateUrl: 'views/addCandidate.html',
            controller: 'addCandidate',
        })
        .when('/uploadCV', {
            templateUrl: 'views/addCV.html',
            controller: 'addCvCtrl',
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
        queryGet: function(url) {
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
        },
        queryPost: function(url,dataPost) {
            var URL = url;
            var dataPost = dataPost
            var data = $http({
                method: 'POST',
                data: dataPost,
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
