define([
    'jquery',
    'underscore',
    'backbone',
    'model/flow/flow-action-model',
    'text!template/flow/flow-action-editor.html'
], function ($, _, Backbone, FlowActionModel, actionEditorTemplate) {
    var FlowActionEditor = Backbone.View.extend({
        //el: $("#new-action-panel"),
        model: new FlowActionModel(),

        initialize: function (options) {
            this.template = _.template(actionEditorTemplate);
            this.model = options.model;
        },
        render: function () {
            this.$el.html(this.template(this.model.attributes));
            return this;
        },

        events: {
            'click #add-action-btn': 'addAction'
        },

        addAction: function (e) {
            this.model.set('description', this.$el.find('textarea[id="description"]').val());
            this.model.set('state', this.$el.find('select[id="state"]').val());
            this.model.save();
            return false;
        }
    });
    return FlowActionEditor;
});