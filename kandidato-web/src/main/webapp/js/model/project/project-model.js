define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var ProjectModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/project',
        defaults: {
            id: null,
            name: "",
            description: ""
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return ProjectModel;
});