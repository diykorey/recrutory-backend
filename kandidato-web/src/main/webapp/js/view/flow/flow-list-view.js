define([
    'jquery',
    'underscore',
    'backbone',
    'collection/flow/flow-collection',
    'view/flow/flow-row-view',
    'text!template/flow/flow-list.html'
], function ($, _, Backbone, FlowCollection, RowView, flowList) {
    var FlowListView = Backbone.View.extend({

        initialize: function () {
            var compiledTemplate = _.template(flowList);
            this.$el.html(compiledTemplate);
        },
        render: function () {
            var $tbody = this.$("tbody");
            _.each(this.model.models, function (data) {
                $tbody.append(new RowView({model: data}).render().el);
            }, this);
            return this;
        }
    });
    return FlowListView;
});