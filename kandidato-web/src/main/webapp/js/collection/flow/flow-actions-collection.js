define([
    'underscore',
    'backbone',
    'model/flow/flow-action-model'
], function(_, Backbone, FlowActionModel){
    var FlowActionsCollection = Backbone.Collection.extend({
        model: FlowActionModel,
        urlRoot: "workflow",

        url: function() {
            return this.instanceUrl;
        },
        initialize: function (options) {
            this.instanceUrl = this.urlRoot + '/actions/' + options.flowId;
        }
    });
    return FlowActionsCollection;
});