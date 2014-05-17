define([
    'underscore',
    'backbone',
    'model/candidate/candidate-model'
], function(_, Backbone, CandidateModel){
    var CandidateCollection = Backbone.Collection.extend({
        model: CandidateModel
    });
    return CandidateCollection;
});