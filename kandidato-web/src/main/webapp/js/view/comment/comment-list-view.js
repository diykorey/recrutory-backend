define([
    'jquery',
    'underscore',
    'backbone',
    'collection/comment/comment-collection',
    'view/comment/comment-row-view',
    'text!template/comment/comment-list.html'
], function ($, _, Backbone, CommentCollection, RowView, commentList) {
    var CommentListView = Backbone.View.extend({

        initialize: function () {
            var compiledTemplate = _.template(commentList);
            this.$el.html(compiledTemplate);
        },
        render: function () {
            var $tbody = this.$("tbody");
            _.each(this.model.models, function (data) {
                $tbody.append(new RowView({model: data}).render().el);
            }, this);
            return this;
        }
    });
    return CommentListView;
});