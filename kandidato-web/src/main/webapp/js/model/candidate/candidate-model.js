define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var CandidateModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/candidate',
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