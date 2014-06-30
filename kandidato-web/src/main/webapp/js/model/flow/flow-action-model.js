define([
    'underscore',
    'backbone'
], function (_, Backbone) {
    var FlowActionModel = Backbone.Model.extend({
        urlRoot: 'workflow',
        url: function () {
            return this.urlRoot + '/flow-action/' + this.id;
        },

        defaults:{
            description : ""
        },

        initialize: function (options) {
            if (typeof options != 'undefined') {
                this.flowId = options.flowId;
            }
        },

        sync: function (method, model, options) {
            if (method == 'create') {
                options.url = this.urlRoot + '/flow-action/' + model.flowId;
            }
            return Backbone.sync(method, model, options);
        }
    });
    return FlowActionModel;
});