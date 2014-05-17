define([
    'jquery',
    'underscore',
    'backbone',
    'collection/candidate/candidates-collection',
    'text!template/candidate/candidate-list-row.html'
], function($, _, Backbone, CandidateCollection, candidateListRow){
    var CandidateListView = Backbone.View.extend({
        el: $("#container"),
        initialize: function(){
            $('.nav li').removeClass('active');
            $('.nav li a[href="' + window.location.hash + '"]').parent().addClass('active');
            this.collection = new CandidateCollection();
            var compiledTemplate = _.template( candidateListRow, { candidates: this.collection.models } );
            this.$el.html(compiledTemplate);
        }
    });
    return CandidateListView;
});