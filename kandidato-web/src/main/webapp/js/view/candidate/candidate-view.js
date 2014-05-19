define([
    'jquery',
    'underscore',
    'backbone',
    'model/candidate/candidate-model',
    'text!template/candidate/candidate.html'
], function ($, _, Backbone, CandidateModel, candidateTemplate) {

    var CandidateView = Backbone.View.extend({
        el: $("#container"),
        model: new CandidateModel(),
        initialize: function() {
            this.template = _.template(candidateTemplate);
            this.listenTo(this.model, 'change', this.render);
        },
        render: function () {
            this.model = new CandidateModel({ id: 1 });
            this.model.fetch();

            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });

    return CandidateView;
});