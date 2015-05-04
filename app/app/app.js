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
    'ngAnimate',
    'ngSanitize',
    'MassAutoComplete'
])

kandidatoApp.$inject = ['$scope', 'ApiDataFactory'];
