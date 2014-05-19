define([
    'jquery',
    'underscore',
    'backbone',
    'model/candidate/candidate-model',
    'text!template/candidate/candidate.html'
], function ($, _, Backbone, CandidateModel, candidateTemplate) {

    var CandidateView = Backbone.View.extend({
        candidateId: null,
        el: $("#container"),
        model: new CandidateModel(),
        initialize: function () {
            this.template = _.template(candidateTemplate);
            this.model = new CandidateModel();
            this.listenTo(this.model, 'change', this.render);
            this.model.id = this.candidateId;
            this.model.fetch();
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return CandidateView;
});