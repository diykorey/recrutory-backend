'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('NavCtrl', function($scope, $rootScope, $location, $timeout) {
    var tabs = [{
        heading: 'images/logo.png',
        type: 'image',
        route: '/'
    }, {
        heading: 'Dashboard',
        type: 'link',
        route: 'dashboard'
    }, {
        heading: 'Vacancies list',
        type: 'link',
        route: 'list'
    }, {
        heading: 'Candidate Dashboard',
        type: 'link',
        route: 'candidateDashboard'
    }, {
        heading: 'Candidate List',
        type: 'link',
        route: 'candidateList'
    }];



    $timeout(function() {
        $scope.selectedIndex = 1
    }, 0);


    $scope.tabs = tabs;


    function navigate(view) {

    }

    $scope.navigate = navigate


    function route(hash) {
        $location.path(hash);
    }


    $scope.route = route;

});
