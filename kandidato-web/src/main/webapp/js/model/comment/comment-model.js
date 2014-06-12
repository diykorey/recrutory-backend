define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var CommentModel = Backbone.Model.extend({
        urlRoot: 'comment',
        defaults: {
            id: null,
            comment: "",
            entityId: null,
            authorId: null,
            entityType: ""
        },
        initialize: function (options) {
            if (typeof options != 'undefined') {
                this.entityId = options.entityId;
                this.entityType = options.entityType;
            }
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        },
        sync: function (method, model, options) {
            if (method == 'POST') {
                options.url = 'comment/' + model.entityId + '/' + model.authorId + '/' + model.entityType;
            } else {
                options.url = model.url;
            }
            return Backbone.sync(method, model.comment, options);
        }
    });
    return CommentModel;
});