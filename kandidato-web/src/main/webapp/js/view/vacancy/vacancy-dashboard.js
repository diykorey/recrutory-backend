define([
    'jquery',
    'underscore',
    'backbone',
    'select2',
    'model/vacancy/vacancy-model',
    'model/tag/tag-model',
    'model/project/project-model',

    'text!template/vacancy/vacancy-dashboard.html'
], function ($, _, Backbone, VacancyModel, vacanciesTemplate) {

    var VacancyDashboard = Backbone.View.extend({
        el: $("#container"),
        render: function () {
            this.$el.html(vacanciesTemplate);
        }
    });

    return VacancyDashboard;
});