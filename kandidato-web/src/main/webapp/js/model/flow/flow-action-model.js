define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var FlowActionModel = Backbone.Model.extend({
        urlRoot: 'flow-action',
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return FlowActionModel;
});