define([
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model'
], function (_, Backbone, VacancyModel) {
    var VacanciesCollection = Backbone.Collection.extend({
        urlRoot: 'vacancy/byAuthor',
        model: VacancyModel,
        initialize: function(options) {
            this.instanceUrl = this.urlRoot + '/' + options.author;
        },
        url: function() {
            return this.instanceUrl;
        }
    });
    return VacanciesCollection;
});