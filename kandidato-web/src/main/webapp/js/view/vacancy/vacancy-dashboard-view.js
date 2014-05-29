define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'model/vacancy/vacancy-list-model',
    'view/vacancy/vacancy-widget-view',
    'text!template/vacancy/vacancy-dashboard.html'
], function ($, _, Backbone, VacancyModel, VacancyListModel, VacancyWidget, dashboardTemplate) {

    var VacancyDashboard = Backbone.View.extend({
        el: $("#container"),


        initialize: function (options) {
            this.vacancies = new VacancyListModel({author: options.author});
            this.template = _.template(dashboardTemplate);
        },

        render: function () {
            var $el = $(this.el);
            $el.html(this.template);
            this.vacancies.each(function (vacancy) {
                var vacancyWidget = new VacancyWidget({model: vacancy});
                $el.append(vacancyWidget.render().el);
            });
            return this;
        },
        start: function () {
            var dashboard = this;
            this.vacancies.fetch({
                success: function () {
                    console.log(dashboard);
                    dashboard.render();
                }
            });
        }
    });

    return VacancyDashboard;
});