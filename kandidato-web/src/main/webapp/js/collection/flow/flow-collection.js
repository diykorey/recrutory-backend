define([
    'underscore',
    'backbone',
    'model/flow/flow-model'
], function(_, Backbone, FlowModel){
    var FlowCollection = Backbone.Collection.extend({
        model: FlowModel,
        urlRoot: "workflow",

        url: function() {
            return this.instanceUrl;
        },
        initialize: function (options) {
            if (typeof options != 'undefined' && typeof options.criteria != 'undefined') {
                this.instanceUrl = this.urlRoot + '/' + options.criteria;
            } else {
                this.instanceUrl = this.urlRoot + '/flows';
            }
        }
    });
    return FlowCollection;
});