'use strict';

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