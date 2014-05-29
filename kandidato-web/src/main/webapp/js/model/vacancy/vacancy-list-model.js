define([
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model'
], function (_, Backbone, VacancyModel) {
    var VacancyListModel = Backbone.Collection.extend({
        urlRoot: 'http://localhost:8080/vacancy/byAuthor',
        model: VacancyModel,
        initialize: function(options) {
//            this.instanceUrl = this.urlRoot + '/' + options.author;
            this.instanceUrl = 'http://localhost:8080/vacancy/byState/OPEN';
        },
        url: function() {
            return this.instanceUrl;
        }
    });
    return VacancyListModel;
});