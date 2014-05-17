define([
    'jquery',
    'underscore',
    'backbone',
    'model/candidate/candidate-model',
    'collection/candidate/candidates-collection',
    'view/candidate/candidate-list-view',
    'text!template/candidate/candidate-list.html'
], function ($, _, Backbone, CandidateModel, CandidatesCollection, CandidateListView, candidatesTemplate) {

    var CandidatesView = Backbone.View.extend({
        el: $("#container"),
        render: function () {
            this.$el.html(candidatesTemplate);

            var candidate0 = new CandidateModel({name: 'test1', lastName: 'Test'});
            var candidate1 = new CandidateModel({name: 'test2', lastName: 'Test'});
            var candidate2 = new CandidateModel({name: 'test3', lastName: 'Test'});
            var candidate3 = new CandidateModel({name: 'test4', lastName: 'Test'});
            var candidate4 = new CandidateModel({name: 'test5', lastName: 'Test'});

            var aProjects = [candidate0,
                candidate1,
                candidate2,
                candidate3,
                candidate4];

            var candidatesCollection = new CandidatesCollection(aProjects);
            var candidatesListView = new CandidateListView({ collection: candidatesCollection});

            candidatesListView.render();
        }
    });

    return CandidatesView;
});