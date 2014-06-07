define([
    'jquery',
    'underscore',
    'backbone',
    'model/comment/comment-model',
    'text!template/comment/comment-row.html'
], function ($, _, Backbone, CommentModel, rowTemplate) {

    var CommentRow = Backbone.View.extend({
        model: new CommentModel(),
        el: $('<tr></tr>'),
        initialize: function () {
            this.template = _.template(rowTemplate);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return CommentRow;
});