define([
    'jquery',
    'underscore',
    'backbone',
    'model/vacancy/vacancy-model',
    'text!template/vacancy/vacancy-editor.html'
], function ($, _, Backbone, VacancyModel, vacancyEditorTemplate) {

    var VacancyEditor = Backbone.View.extend({
        model: new VacancyModel(),

        initialize: function (options) {
            this.model = options.model;
            this.template = _.template(vacancyEditorTemplate);
            this.listenTo(this.model, 'change', this.render);
            //FIXME replace hardcoded URL
//            this.addTypeahead("projectName", "#projectName", "http://localhost:8080/project/find");
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        }
//        addTypeahead: function (name, objectId, sourceUrl) {
//            var source = new Bloodhound({
//                datumTokenizer: function (d) {
//                    return Bloodhound.tokenizers.whitespace(d.name);
//                },
//                queryTokenizer: Bloodhound.tokenizers.whitespace,
//                limit: 20,
//                remote: sourceUrl + '/%QUERY'
//            });
//            source.initialize();
//
//            $(objectId).typeahead({
//                    minLength: 1,
//                    highlight: true
//                },
//                {
//                    name: name,
//                    displayKey: 'name',
//                    source: source.ttAdapter()
//                }
//            );
//            $(objectId).on('typeahead:selected', function (event, data) {
//                activeVacancy.project = data;
//                $('projectDescription').val(data.description);
//
//            });
//
//            $(objectId).on('input propertychange', function () {
//                var value = $(this).val();
//                if (!activeVacancy.project) {
//                    var project = new Project();
//                    activeVacancy.project = project;
//                }
//                activeVacancy.project.name = value;
//            });
//        }

    });
    return VacancyEditor;
});