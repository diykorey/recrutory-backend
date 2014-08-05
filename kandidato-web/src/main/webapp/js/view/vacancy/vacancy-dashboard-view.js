define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'collection/vacancy/vacancy-collection',
    'view/vacancy/vacancy-widget-view',
    'text!template/vacancy/vacancy-dashboard.html'
], function ($, _, Backbone, VacancyModel, VacanciesCollection, VacancyWidget, dashboardTemplate) {

    var VacancyDashboard = Backbone.View.extend({
        el: $("#container"),
        initialize: function (options) {
            this.widgets = [];
            this.vacancies = new VacanciesCollection({author: options.author});
            this.template = _.template(dashboardTemplate);
            Backbone.on('vacancySelected', this.onSelectionChange);
        },
        render: function () {
            var $el = $(this.el);
            $el.html(this.template);
            var _this = this;
            this.vacancies.each(function (vacancy) {
                var vacancyWidget = new VacancyWidget({model: vacancy, selected: false});
                _this.widgets.push(vacancyWidget);
                $el.append(vacancyWidget.render().el);
            });
            return this;
        },
        start: function () {
            var dashboard = this;
            this.vacancies.fetch({
                success: function () {
                    dashboard.render();
                }
            });
        },
        onSelectionChange: function(widget) {
            var vacancyElement = $('#vacancy-'+widget.model.attributes.number);
            $("div[id^='vacancy-']").each(function(i, obj) {
                $(obj).removeClass('toogle');
            });
            $(vacancyElement).addClass('toogle');

        }
    });

    return VacancyDashboard;
});