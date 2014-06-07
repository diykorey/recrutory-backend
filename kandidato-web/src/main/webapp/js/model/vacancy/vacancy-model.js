define([
    'underscore',
    'backbone',
    'model/project/project-model'
], function (_, Backbone, ProjectModel) {
    var VacancyModel = Backbone.Model.extend({
        urlRoot: 'vacancy',
        defaults: {
            id: null,
            number: null,
            name: null,
            state: "OPEN",
            hot: false,
            requirements: '',
            tags: [],
            project: new ProjectModel()
        },
        url: function () {
            return this.urlRoot + '/' + this.id;
        }
    });
    return VacancyModel;
});