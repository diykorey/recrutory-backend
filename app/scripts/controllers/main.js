'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('MainCtrl', function($scope, $rootScope, $location, $mdToast) {
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




    $scope.showToast = function() {
        $mdToast.show(
            $mdToast.simple()
            .content('Got it ! Updated.')
            .position('right top')
            .hideDelay(3000)
        );
    };

    $scope.showCustomToast = function() {
        $mdToast.show({
            controller: 'dashCtrl',
            templateUrl: 'views/partials/undo-template.html',
            hideDelay: 3000,
            position: "right top"
        });
    };

    $scope.tabs = tabs;


    function navigate(view) {

    }

    $scope.navigate = navigate


    function route(hash) {
        $location.path(hash);
    }


    $scope.route = route;

});
