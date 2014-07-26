define([
    'underscore',
    'backbone',
    'model/project/project-model'
], function (_, Backbone, ProjectModel) {
    var ProjectCollection = Backbone.Collection.extend({

        urlRoot: 'project/find',
        model: ProjectModel,
        initialize: function(options) {
           this.instanceUrl = this.urlRoot;
        },
        url: function() {
            return this.instanceUrl;
        }
    });

    return ProjectCollection;
});