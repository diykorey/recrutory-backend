define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'collection/flow/flow-collection',
    'view/vacancy/vacancy-editor-view',
    'view/flow/flow-list-view',
    'text!template/vacancy/vacancy-details.html'
], function ($, _, Backbone, VacancyModel, FlowCollection, VacancyEditor, FlowListView, detailsTemplate) {

    var VacancyDetails = Backbone.View.extend({
        el : $('#details'),
        events: {
            'click #editor-link': 'showDetails',
            'click #flows-link': 'showFlows'
        },
        showDetails: function() {
            var vacancyEditor = new VacancyEditor({model: this.model});
            $('#vacancy-details-tab').html(vacancyEditor.render().el);
        },
        showFlows: function() {
            var flowCollection = new FlowCollection({criteria : '/byVacancy/' + this.model.id});
            flowCollection.fetch({
                success: function() {
                    var flowList = new FlowListView({model: flowCollection});
                    $('#vacancy-details-tab').html(flowList.render().el);
                }
            });
        },

        initialize: function (options) {
            this.model = options.model;
            this.template = _.template(detailsTemplate);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
    });
    return VacancyDetails;
});