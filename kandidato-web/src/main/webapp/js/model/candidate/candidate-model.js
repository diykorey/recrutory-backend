define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var CandidateModel = Backbone.Model.extend({
        urlRoot: 'candidate',
        defaults: {
            id: null,
            name: '',
            lastName: '',
            createTime: new Date(),
            position: ''
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return CandidateModel;
});