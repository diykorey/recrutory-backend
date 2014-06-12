define([
    'jquery',
    'underscore',
    'backbone',
    'collection/comment/comment-collection',
    'view/comment/comment-row-view',
    'view/comment/comment-editor-view',
    'model/comment/comment-model',
    'text!template/comment/comment-list.html'
], function ($, _, Backbone, CommentCollection, RowView, CommentEditor, CommentModel, commentList) {
    var CommentListView = Backbone.View.extend({

        entityId: null,
        entityType: null,

        initialize: function (options) {
            this.entityId = options.entityId;
            this.entityType = options.entityType;
            var compiledTemplate = _.template(commentList);
            this.$el.html(compiledTemplate);
        },
        render: function () {
            var $listBody = this.$("#comment-list");
            $listBody.append(new CommentEditor({model: new CommentModel({entityId: this.entityId, entityType: this.entityType })}).render().el)

            _.each(this.model.models, function (data) {
                $listBody.append(new RowView({model: data}).render().el);
            }, this);
            return this;
        }
    });
    return CommentListView;
});