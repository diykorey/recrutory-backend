define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var VacancyModel = Backbone.Model.extend({
        url: function () {
            return 'http://localhost:8080/vacancy/';
        }
    });
    return VacancyModel;
});