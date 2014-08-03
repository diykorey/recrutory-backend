define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'collection/flow/flow-collection',
    'collection/comment/comment-collection',
    'view/vacancy/vacancy-editor-view',
    'view/flow/flow-list-view',
    'view/comment/comment-list-view',
    'text!template/vacancy/vacancy-details.html'
], function ($, _, Backbone, VacancyModel, FlowCollection, CommentCollection, VacancyEditor, FlowListView, CommentListView, detailsTemplate) {

    var VacancyDetails = Backbone.View.extend({
        el : $('#details'),
        events: {
            'click #editor-link': 'showDetails',
            'click #flows-link': 'showFlows',
            'click #comments-link': 'showComments'
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
        showComments: function() {
            var commentCollection = new CommentCollection({criteria : 'VACANCY/'+ this.model.id});
            var vacancyModel = this.model;
            commentCollection.fetch({
                success: function() {
                    var commentList = new CommentListView({model: commentCollection, entityId: vacancyModel.id, entityType: 'VACANCY'});
                    $('#vacancy-details-tab').html(commentList.render().el);
                }
            });
        },

        initialize: function (options) {
            this.model = options.model;
            this.template = _.template(detailsTemplate);
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            this.showDetails();
            return this;
        }
    });
    return VacancyDetails;
});