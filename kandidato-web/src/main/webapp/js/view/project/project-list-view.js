define([
    'jquery',
    'underscore',
    'backbone',
    'collection/project/project-collection',
    'text!template/project/project-list.html'
], function ($, _, Backbone, ProjectCollection, projectList) {
    var ProjectListView = Backbone.View.extend({
        el: $("#container"),
        initialize: function (options) {
            this.projects = new ProjectCollection();
            this.template = _.template(projectList);
        },

        render: function () {
            this.$el.html(this.template( this.projects));
            return this;
        },
        start: function () {
            var listView = this;
            this.projects.fetch({
                success: function () {
                    listView.render();
                }
            });
        }
    });
    return ProjectListView;
});