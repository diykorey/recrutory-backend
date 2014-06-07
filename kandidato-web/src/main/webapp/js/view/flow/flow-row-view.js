define([
    'jquery',
    'underscore',
    'backbone',
    'model/flow/flow-model',
    'text!template/flow/flow-row.html'
], function ($, _, Backbone, FlowModel, rowTemplate) {

    var VacancyWidget = Backbone.View.extend({
        model: new FlowModel(),
        el: $('<tr></tr>'),
        initialize: function () {
            this.template = _.template(rowTemplate);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return VacancyWidget;
});