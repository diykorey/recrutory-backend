define([
    'jquery',
    'underscore',
    'backbone',
    'collection/flow/flow-collection',
    'text!template/flow/flow-actions.html'
], function ($, _, Backbone, FlowCollection, actionsTemplate) {

    var FlowActions = Backbone.View.extend({
        el: $("#details"),
        flowId : null,

        render: function () {
            var compiled = _.template(actionsTemplate, {flowId : this.flowId});
            this.$el.html(compiled);
        }
    });
    return FlowActions;
});
