'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('vacancyListCtrl', function($scope, $rootScope, $log, ApiDataFactory, $timeout) {

    $scope.filterValues = {}
        /**
         * Filter
         */

    $scope.filters = [{
            name: "Open"
        }, {
            name: "Hot"
        }

    ]

    $scope.filterVacancies = function() {
        _.each($scope.filterValues, function(value, filter) {
            value.value ? value.filter = value.value : value.filter = '';
        });

    }

});
