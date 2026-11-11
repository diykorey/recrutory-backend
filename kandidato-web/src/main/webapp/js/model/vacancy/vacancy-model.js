define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var VacancyModel = Backbone.Model.extend({
        urlRoot: 'vacancy',
        defaults: {
            id: null,
            number: null,
            name: null,
            state: "OPEN",
            hot: false,
            requirements: '',
            tags: []
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return VacancyModel;
});