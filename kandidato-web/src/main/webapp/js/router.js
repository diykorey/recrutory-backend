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
    'view/vacancy/vacancy-dashboard',
    'view/flow/flow-dashboard-view'

], function ($, _, Backbone, CandidateView, CandidatesView, VacancyDashboard, FlowDashboard) {
    var AppRouter = Backbone.Router.extend({
        routes: {
            'vacancy-dashboard': 'vacancyDashboard',
            'flow-dashboard': 'flowDashboard',
            'candidate': 'candidate',
            'candidates': 'candidates',
            // Default
            '': 'home'
        }
    });

    var initialize = function () {
        var app_router = new AppRouter;
        app_router.on('route:flowDashboard', function () {
            menuUpdate();
            var flowDashboardView = new FlowDashboard();
            flowDashboardView.render();
        });
        app_router.on('route:candidate', function () {
            menuUpdate();
            var candidateView = new CandidateView();
            candidateView.render();
        });
        app_router.on('route:candidates', function () {
            menuUpdate();
            var candidateListView = new CandidatesView();
            candidateListView.render();
        });
        app_router.on('route:vacancyDashboard', function () {
            menuUpdate();
            var vacancyDashboard = new VacancyDashboard();
            vacancyDashboard.render();
        });
        app_router.on('route:home', function () {
            menuUpdate();
            var vacancyDashboard = new VacancyDashboard();
            vacancyDashboard.render();
        });
        var menuUpdate = function() {
            $('.nav li').removeClass('active');
            $('.nav li a[href="' + window.location.hash + '"]').parent().addClass('active');
        }
        Backbone.history.start();
    };
    return {
        initialize: initialize
    };
});