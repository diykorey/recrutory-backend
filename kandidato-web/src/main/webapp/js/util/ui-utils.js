define([
    'jquery'
], function ($) {
    var UiUtils
    var showSidePanel = function() {
        $.el('container').removeClass('col-sm-12 col-xs-12 col-lg-12');
        $.el('details').removeClass('col-sm-0 col-xs-0 col-lg-0');
        $.el('details').addClass('col-sm-6 col-xs-6 col-lg-6');
        $.el('container').addClass('col-sm-6 col-xs-6 col-lg-6');
    }
})

