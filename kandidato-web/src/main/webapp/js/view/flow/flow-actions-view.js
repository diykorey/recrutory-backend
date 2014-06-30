define([
    'jquery',
    'underscore',
    'backbone',
    'collection/flow/flow-actions-collection',
    'model/flow/flow-action-model',
    'view/flow/action-widget-view',
    'view/flow/action-editor-view',
    'text!template/flow/flow-actions.html'
], function ($, _, Backbone, FlowActionsCollection, FlowActionModel, ActionWidget, FlowActionEditor, actionsTemplate) {

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

                    var editor = new FlowActionEditor({ model: new FlowActionModel({flowId: that.flowId})});
                    $("#flow-action-accordion").append(editor.render().el);

                    actions.each(function (action) {
                        var actionWidget = new ActionWidget({model: action});
                        $("#flow-action-accordion").append(actionWidget.render().el);
                    });
                    return this;
                }
            })
        }
    });
    return FlowActions;
});
