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
            _.each(this.models, function(model, index, list) {

            });
            this.$el.html(flowsTemplate);
        }
    });
    return FlowDashboard;
});