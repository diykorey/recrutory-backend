define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var TagModel = Backbone.Model.extend({
        urlRoot: 'tag',
        defaults: {
            id: null,
            keyword: ""
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return TagModel;
});