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
            if (method == 'create') {
                options.url = 'comment/' + model.entityType + '/' + model.entityId;
                options.beforeSend = function(xhr) {
                    xhr.setRequestHeader('Content-Type', 'text/plain');
                };
            } else {
                options.url = model.url();
            }
            return Backbone.sync(method, model, options);
        },
        toJSON: function() {
            return this.attributes.comment;
        }
    });
    return CommentModel;
});