define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var CustomerModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/customer',
        defaults: {
            id: null,
            name: "",
            description: "",
            creationTime: ""
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return CustomerModel;
});