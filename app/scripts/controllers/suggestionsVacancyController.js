'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('suggestionsView', function($scope, $rootScope, $log, ApiDataFactory) {



    _.each($scope.flowData, function(item) {

        alert(item)

    });





    function addComment(candidateSelected) {
        _.each($scope.dataSuggectionsSimulated, function(candidate) {

            if (candidate.name === candidateSelected) {
                candidate.addComment = true
                candidate.commentTemp = candidate.comment
                if (candidate.commentTemp == false) {
                    candidate.commentTemp = ""
                };
            };

        });
    }


    _.each($scope.dataSuggectionsSimulated, function(candidate) {

        if (candidate.status === 'Interview') {
            candidate.sortLevel = 1
        };
        if (candidate.status === 'Offer') {
            candidate.sortLevel = 2
        };
        if (candidate.status === 'Rejected') {
            candidate.sortLevel = 0
        };
        if (candidate.status === 'Hired') {
            candidate.sortLevel = 3
        };

    });

    $scope.addComment = addComment


});
