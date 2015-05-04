'use strict';

kandidatoApp.config(function($routeProvider) {
    $routeProvider
        .when('/dashboard', {
            templateUrl: 'app/views/vacancyDashboard.html',
            controller: 'vacancyCtrl'
        })
        .when('/list', {
            templateUrl: 'app/views/vacancyList.html',
            controller: 'vacancyCtrl'
        })
        .when('/addVacancy', {
            templateUrl: 'app/views/addVacancy.html',
            controller: 'addVacancyCtrl',
        })
        .when('/addCandidate', {
            templateUrl: 'app/views/addCandidate.html',
            controller: 'addCandidate',
        })
        .when('/uploadCV', {
            templateUrl: 'app/views/addCV.html',
            controller: 'addCvCtrl',
        })
        .when('/candidateDashboard', {
            templateUrl: 'app/views/candidateDashboard.html',
            controller: 'candidateCtrl'
        })
        .when('/candidateList', {
            templateUrl: 'app/views/candidateList.html',
            controller: 'candidateCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
});
