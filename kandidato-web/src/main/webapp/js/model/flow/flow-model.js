define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var FlowModel = Backbone.Model.extend({
        urlRoot: 'flow',
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return FlowModel;
});