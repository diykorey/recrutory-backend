define([
    'jquery',
    'underscore',
    'backbone',
    'collection/project/project-collection',
    'text!template/project/project-list.html'
], function ($, _, Backbone, ProjectCollection, projectList) {
    var ProjectListView = Backbone.View.extend({
        el: $("#container"),
        initialize: function () {
            this.collection = new ProjectCollection();
            var compiledTemplate = _.template(projectList, { candidates: this.collection.models });
            this.$el.html(compiledTemplate);
        }
    });
    return ProjectListView;
});