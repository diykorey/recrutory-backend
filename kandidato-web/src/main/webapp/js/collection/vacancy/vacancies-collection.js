define([
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model'
], function(_, Backbone, VacancyModel){
    var VacancyCollection = Backbone.Collection.extend({
        model: VacancyModel
    });
    return VacancyCollection;
});