define([
    'jquery',
    'underscore',
    'backbone',
    'collection/flow/flow-collection',
    'text!template/flow/flow-dashboard.html',
    'view/flow/flow-widget-view'
], function ($, _, Backbone, FlowCollection, flowsTemplate, FlowWidget) {

    var FlowDashboard = Backbone.View.extend({
        el: $("#container"),

        render: function () {
            var that = this;

            this.collection = new FlowCollection();
            this.collection.fetch({
                success: function (flows) {
                    var $el = $(that.el);
                    $el.html(_.template(flowsTemplate));
                    flows.each(function (flow) {
                        var flowWidget = new FlowWidget({model: flow});
                        $el.append(flowWidget.render().el);
                    });
                    return this;
                }
            })
        }
    });
    return FlowDashboard;
});