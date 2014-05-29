/**
 * This file is used for navigation in the application using URLs.
 *
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'view/candidate/candidate-view',
    'view/candidate/candidates-view',
    'view/vacancy/vacancy-dashboard-view',
    'view/flow/flow-dashboard-view'

], function ($, _, Backbone, CandidateView, CandidatesView, VacancyDashboard, FlowDashboard) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            'vacancy-dashboard': 'vacancyDashboard',
            'flow-dashboard': 'flowDashboard',
            'candidate/:id': 'candidate',
            'candidates': 'candidates',
            'projects': 'projects',
            // Default
            '': 'home'
        }
    });

    var initialize = function () {
        var app_router = new AppRouter;
        app_router.on('route:flowDashboard', function () {
            var flowDashboardView = new FlowDashboard();
            flowDashboardView.render();
        });
        app_router.on('route:candidate', function (id) {
            var candidateView = new CandidateView();
            candidateView.candidateId = id;
            candidateView.render();
        });
        app_router.on('route:candidates', function () {
            var candidateListView = new CandidatesView();
            candidateListView.render();
        });
        app_router.on('route:vacancyDashboard', function () {
            var vacancyDashboard = new VacancyDashboard({author: 1});
            vacancyDashboard.start();
        });
        app_router.on('route:projects', function () {
        });
        app_router.on('route:home', function () {
            var vacancyDashboard = new VacancyDashboard({author: 1});
            vacancyDashboard.start();
        });


        Backbone.history.bind("all", function () {
            $('.nav li').removeClass('active');
            $('.nav li a[href="' + window.location.hash + '"]').parent().addClass('active');
        });
        Backbone.history.start();
    };
    return {
        initialize: initialize
    };
});