/**
 * This file configures require js and launches the application.
 */
require.config({
    paths: {
        jquery: 'lib/jquery/jquery-2.1.0.min',
        bootstrap: 'lib/bootstrap/js/bootstrap',
        underscore: 'lib/underscore/underscore',
        backbone: 'lib/backbone/backbone'
    },
    shim: {
        'backbone': {
            //These script dependencies should be loaded before loading
            //backbone.js
            deps: ['underscore', 'jquery'],
            //Once loaded, use the global 'Backbone' as the
            //module value.
            exports: 'Backbone'
        },
        'underscore': {
            exports: '_'
        }
    }
});

require([
    // Load our app module and pass it to our definition function
    'app'
], function (App) {
    // The "app" dependency is passed in as "App"
    App.initialize();
});