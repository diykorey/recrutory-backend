define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'text!template/vacancy/vacancy-dashboard.html'
], function ($, _, Backbone, VacancyModel, vacanciesTemplate) {

    var VacancyDashboard = Backbone.View.extend({
        el: $("#container"),
        render: function () {
            $('.nav li').removeClass('active');
            $('.nav li a[href="' + window.location.hash + '"]').parent().addClass('active');
            this.$el.html(vacanciesTemplate);
        }
    });

    return VacancyDashboard;
});