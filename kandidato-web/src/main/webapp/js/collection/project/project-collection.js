define([
    'underscore',
    'backbone',
    'model/project/project-model'
], function (_, Backbone, ProjectModel) {
    var ProjectCollection = Backbone.Collection.extend({
        model: ProjectModel
    });

    return ProjectCollection;
});