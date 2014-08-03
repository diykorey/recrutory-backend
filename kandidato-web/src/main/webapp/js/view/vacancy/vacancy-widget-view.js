define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'view/vacancy/vacancy-details-view',
    'text!template/vacancy/vacancy-widget.html'
], function ($, _, Backbone, VacancyModel, VacancyDetails, vacancyTemplate) {

    var VacancyWidget = Backbone.View.extend({
        model: new VacancyModel(),
        selected: false,
        events: {
            "click .clickable": "showVacancyDetails"
        },
        showVacancyDetails: function() {
            this.model.selected = true;
            var vacancyDetails = new VacancyDetails({model: this.model});
            vacancyDetails.render();
        },
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