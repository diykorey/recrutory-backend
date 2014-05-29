define([
    'jquery',
    'underscore',
    'backbone',
    'collection/vacancy/vacancies-collection',
    'text!template/vacancy/vacancy-list-row.html'
], function ($, _, Backbone, VacancyCollection, vacancyListRow) {
    var CandidateListView = Backbone.View.extend({
        el: $("#container"),
        initialize: function () {
            this.collection = new VacancyCollection();
            var compiledTemplate = _.template(vacancyListRow, { candidates: this.collection.models });
            this.$el.html(compiledTemplate);
        }
    });
    return CandidateListView;
});