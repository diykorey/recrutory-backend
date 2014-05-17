define([
    'jquery',
    'underscore',
    'backbone',
    'model/flow/flow-model',
    'text!template/flow/flow-dashboard.html'
], function ($, _, Backbone, FlowModel, flowsTemplate) {

    var FlowDashboard = Backbone.View.extend({
        el: $("#container"),
        render: function () {
            this.$el.html(flowsTemplate);
        }
    });
    return FlowDashboard;
});