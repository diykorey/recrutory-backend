define([
    'underscore',
    'backbone',
    'model/flow/flow-model'
], function(_, Backbone, FlowModel){
    var FlowCollection = Backbone.Collection.extend({
        model: FlowModel,
        url: "workflow/flows",
        initialize: function () {
            console.log('collections loaded successfully');
        }
    });
    return FlowCollection;
});