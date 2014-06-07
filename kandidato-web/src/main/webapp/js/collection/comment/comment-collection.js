define([
    'underscore',
    'backbone',
    'model/comment/comment-model'
], function(_, Backbone, CommentModel){
    var CommentCollection = Backbone.Collection.extend({
        model: CommentModel,
        urlRoot: "comment",

        url: function() {
            return this.instanceUrl;
        },
        initialize: function (options) {
                this.instanceUrl = this.urlRoot + '/' + options.criteria;
        }
    });
    return CommentCollection;
});