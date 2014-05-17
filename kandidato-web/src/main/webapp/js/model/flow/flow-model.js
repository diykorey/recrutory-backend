define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var FlowModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/flow',
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return FlowModel;
});