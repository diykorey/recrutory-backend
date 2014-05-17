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
            $('.nav li').removeClass('active');
            $('.nav li a[href="' + window.location.hash + '"]').parent().addClass('active');
            this.$el.html(flowsTemplate);
        }
    });
    return FlowDashboard;
});