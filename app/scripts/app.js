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
    $mdThemingProvider.theme('default')
        .primaryPalette('blue', {
            'default': '400', // by default use shade 400 from the pink palette for primary intentions
            'hue-1': '100', // use shade 100 for the <code>md-hue-1</code> class
            'hue-2': '600', // use shade 600 for the <code>md-hue-2</code> class
            'hue-3': 'A100' // use shade A100 for the <code>md-hue-3</code> class
        })
        // If you specify less than all of the keys, it will inherit from the
        // default shades
        .accentPalette('light-blue', {
            'default': '300' // use shade 200 for default, and keep all other shades the same
        });
});


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
