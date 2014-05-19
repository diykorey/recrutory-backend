define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var CandidateModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/candidate',
        defaults: {
            id: null,
            name: 'Mykola',
            lastName: 'Kavf',
            createTime: new Date(),
            position: 'Software engineer'
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return CandidateModel;
});