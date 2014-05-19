/**
 * This file is meant to be application entry point. It should launch URL navigation routing an global application handlers.
 */
define([
    'jquery',
    'underscore',
    'backbone',
    'router'
], function ($, _, Backbone, Router) {
    var initialize = function () {
        Router.initialize();
    }
    return {
        initialize: initialize
    };
});