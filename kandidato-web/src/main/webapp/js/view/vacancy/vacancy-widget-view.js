define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'text!template/vacancy/vacancy-widget.html'
], function ($, _, Backbone, VacancyModel, vacancyTemplate) {

    var VacancyWidget = Backbone.View.extend({
        model: new VacancyModel(),

        initialize: function () {
            this.template = _.template(vacancyTemplate);
            this.listenTo(this.model, 'change', this.render);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return VacancyWidget;
});