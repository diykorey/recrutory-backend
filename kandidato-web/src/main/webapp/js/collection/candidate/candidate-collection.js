define([
    'underscore',
    'backbone',
    'model/candidate/candidate-model'
], function (_, Backbone, CandidateModel) {
    var CandidateCollection = Backbone.Collection.extend({
        urlRoot: 'candidate/byAuthor',
        model: CandidateModel,
        initialize: function(options) {
            this.instanceUrl = this.urlRoot + '/' + options.author;
        },
        url: function() {
            return this.instanceUrl;
        }
    });
    return CandidateCollection;
});