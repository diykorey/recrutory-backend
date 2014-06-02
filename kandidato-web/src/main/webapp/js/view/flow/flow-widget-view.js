define([
    'jquery',
    'underscore',
    'backbone',
    'model/flow/flow-model',
    'view/flow/flow-actions-view',
    'text!template/flow/flow-widget.html'
], function ($, _, Backbone, FlowModel, FlowActions, flowWidgetTemplate) {

    var FlowWidget = Backbone.View.extend({
        model: new FlowModel(),

        initialize: function () {
            this.template = _.template(flowWidgetTemplate);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        },

        events: {
            "click": "showFlowActions"
        },
        showFlowActions: function() {
            var actions = new FlowActions();
            actions.flowId = this.model.id;
            actions.render();
        }
    });
    return FlowWidget;
});