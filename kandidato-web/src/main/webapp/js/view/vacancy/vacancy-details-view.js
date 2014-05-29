define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'view/vacancy/vacancy-editor-view',
    'text!template/vacancy/vacancy-details.html'
], function ($, _, Backbone, VacancyModel, VacancyEditor, detailsTemplate) {

    var VacancyDetails = Backbone.View.extend({
        el : $('#details'),
        events: {
            "click #editor-link": "showDetail"
        },
        showDetail: function() {
            var vacancyEditor = new VacancyEditor({model: this.model});
            $('#vacancy-details-tab').html(vacancyEditor.render().el);
        },

        initialize: function (options) {
            this.model = options.model;
            this.template = _.template(detailsTemplate);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return VacancyDetails;
});