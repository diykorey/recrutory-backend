define([
    'jquery',
    'underscore',
    'backbone',
    'model/comment/comment-model',
    'text!template/comment/comment-editor.html'
], function ($, _, Backbone, CommentModel, editorTemplate) {

    var CommentEditor = Backbone.View.extend({
        model: new CommentModel(),
        events: {
            'click #save-comment-btn': 'saveComment'
        },
        saveComment: function() {
            this.model.save();
        },
        initialize: function (options) {
            this.model = options.model;
            this.template = _.template(editorTemplate);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return CommentEditor;
});