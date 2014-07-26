define([
    'underscore',
    'backbone',
    'model/customer/customer-model'
], function (_, Backbone, CustomerModel) {
    var ProjectModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/project',
        defaults: {
            id: null,
            name: "",
            description: "",
            creationTime: "",
            customer: new CustomerModel()
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return ProjectModel;
});