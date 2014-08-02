/**
 * This file configures require js and launches the application.
 */
require.config({
    paths: {
        jquery: 'lib/jquery/jquery-2.1.0.min',
        bootstrap: 'lib/bootstrap/js/bootstrap',
        underscore: 'lib/underscore/underscore',
        backbone: 'lib/backbone/backbone',
        html5shiv: 'lib/html5shiv/html5shiv',
        respond: 'lib/respond/respond',
        select2: 'lib/select2/js/select2'
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
        'select2': {
            //These script dependencies should be loaded before loading
            //select2.js
            deps: ['jquery', 'html5shiv', 'respond'],
            //Once loaded, use the global 'Select2' as the
            //module value.
            exports: 'Select2'
        },
        'bootstrap': {
            //These script dependencies should be loaded before loading
            //select2.js
            deps: ['jquery'],
            //Once loaded, use the global 'Select2' as the
            //module value.
            exports: 'bootstrap'
        },
        'jasnybootstrap': {
            //These script dependencies should be loaded before loading
            //select2.js
            deps: ['bootstrap'],
            //Once loaded, use the global 'Select2' as the
            //module value.
            exports: 'jasnybootstrap'
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