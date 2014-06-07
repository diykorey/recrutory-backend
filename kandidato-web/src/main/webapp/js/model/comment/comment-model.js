define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var CommentModel = Backbone.Model.extend({
        urlRoot: 'comment',
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return CommentModel;
});