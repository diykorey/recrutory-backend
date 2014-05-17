define([
    'jquery',
    'underscore',
    'backbone',
    'model/candidate/candidate-model',
    'text!template/candidate/candidate.html'
], function ($, _, Backbone, CandidateModel, candidateTemplate) {

    var CandidateView = Backbone.View.extend({
        el: $("#container"),
        template: _.template(candidateTemplate),
        model: new CandidateModel(),
        render: function () {
            $('.nav li').removeClass('active');
            $('.nav li a[href="' + window.location.hash + '"]').parent().addClass('active');
            this.model.id = 1;
            this.model.fetch();

            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });

    return CandidateView;
});