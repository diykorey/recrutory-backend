'use strict';

kandidatoApp.config(function($routeProvider) {
    $routeProvider
        .when('/dashboard', {
            templateUrl: 'app/views/vacancyDashboard.html',
            controller: 'dashCtrl'
        })
        .when('/list', {
            templateUrl: 'app/views/list.html',
            controller: 'listCtrl'
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
            controller: 'candidateDash'
        })
        .when('/candidateList', {
            templateUrl: 'app/views/candidateList.html',
            controller: 'AboutCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
});
