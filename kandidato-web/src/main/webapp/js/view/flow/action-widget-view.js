define([
    'jquery',
    'underscore',
    'backbone',
    'model/flow/flow-action-model',
    'text!template/flow/action-widget.html'
], function ($, _, Backbone, FlowActionModel, actionWidgetTemplate) {

    var ActionWidget = Backbone.View.extend({
        model: new FlowActionModel(),

        initialize: function () {
            this.template = _.template(actionWidgetTemplate);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return ActionWidget;
});