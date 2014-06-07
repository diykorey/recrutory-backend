define([
    'jquery',
    'underscore',
    'backbone',
    'collection/flow/flow-actions-collection',
    'view/flow/action-widget-view',
    'text!template/flow/flow-actions.html'
], function ($, _, Backbone, FlowActionsCollection, ActionWidget, actionsTemplate) {

    var FlowActions = Backbone.View.extend({
        el: $("#details"),
        flowId : null,

        render: function () {
            var that = this;

            this.collection = new FlowActionsCollection({flowId : this.flowId});
            this.collection.fetch({
                success: function (actions) {
                    var $el = $(that.el);
                    $el.html(_.template(actionsTemplate));
                    actions.each(function (action) {
                        var actionWidget = new ActionWidget({model: action});
                        $el.append(actionWidget.render().el);
                    });
                    return this;
                }
            })
        }
    });
    return FlowActions;
});
