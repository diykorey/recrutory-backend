define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'text!template/vacancy/vacancy-editor.html'
], function ($, _, Backbone, VacancyModel, vacancyEditorTemplate) {

    var VacancyEditor = Backbone.View.extend({
        model: new VacancyModel(),

        initialize: function (options) {
            this.model = options.model;
            this.template = _.template(vacancyEditorTemplate);
            this.listenTo(this.model, 'change', this.render);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return VacancyEditor;
});