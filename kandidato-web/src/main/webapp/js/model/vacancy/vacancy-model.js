define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var VacancyModel = Backbone.Model.extend({
        urlRoot: 'http://localhost:8080/vacancy',
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return VacancyModel;
});