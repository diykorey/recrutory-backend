define([
    'jquery',
    'underscore',
    'backbone',
    'collection/vacancy/vacancy-collection',
    'text!template/vacancy/vacancy-list-row.html'
], function ($, _, Backbone, VacanciesCollection, vacancyListRow) {
    var VacancyListView = Backbone.View.extend({
        el: $("#container"),
        initialize: function () {
            this.collection = new VacanciesCollection();
            var compiledTemplate = _.template(vacancyListRow, { candidates: this.collection.models });
            this.$el.html(compiledTemplate);
        }
    });
    return VacancyListView;
});