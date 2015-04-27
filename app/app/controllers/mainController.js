'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('MainCtrl', function($scope, $rootScope, $location, $mdToast, $timeout) {

    // {
    //      heading: 'assets/images/logo.png',
    //      type: 'image',
    //      route: 'dashboard'
    //  },

    var tabs = [{
        heading: 'Recrutory',
        type: 'link',
        route: 'dashboard'
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

    $scope.tabsVacancies = [{
        heading: 'Main',
    }, {
        heading: 'Flow',

    }, {
        heading: 'Suggestions',

    }, {
        heading: 'Notes',

    }];

    $scope.vacancyActions = [{
        name: "INIT"
    }, {
        name: "CONTACT"
    }, {
        name: "INTERVIEW"
    }, {
        name: "OFFER"
    }, {
        name: "HIRED"
    }, {
        name: "REJECTED"
    }]


    $scope.tabsCandidateDetails = [{
        heading: 'Main',
    }, {
        heading: 'Flow',

    }, {
        heading: 'Suggestions',
    }, {
        heading: 'Notes',

    }];


    // Hack for default tab
    $timeout(function() {
        $scope.selectedIndexMain = 2
    }, 0);
    $timeout(function() {
        $scope.selectedIndexMain = 1
    }, 100);

    $scope.$watch('selectedIndexMain', function(newVal, oldVal) {
        if (newVal == 0) {
            $scope.selectedIndexMain = oldVal
        };
    });

    $scope.showToast = function() {
        $mdToast.show(
            $mdToast.simple()
            .content('Got it ! Updated.')
            .position('right top')
            .hideDelay(3000)
        );
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
