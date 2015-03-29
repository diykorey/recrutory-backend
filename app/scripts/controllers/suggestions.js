'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('suggestionsView', function($scope, $rootScope, $log, ApiDataFactory) {

    $scope.dataSuggectionsSimulated = [{
            name: "Oleh Shostkevych",
            status: "Hired",
            comment: false
        }, {
            name: "Steven Apple",
            status: "Rejected",
            comment: false
        }, {
            name: "Jenifer Lopes",
            status: "Rejected",
            comment: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam gravida dictum dolor id tristique. Curabitur id blandit nisl, sed dictum odio. Nullam pretium lacinia justo sed rhoncus. Nam neque purus, bibendum et dui quis, facilisis congue est. Praesent et neque sed dui convallis lobortis."
        }, {
            name: "Nick Developer",
            status: "Interview",
            comment: false
        }, {
            name: "John Doe",
            status: "Interview",
            comment: "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam gravida dictum dolor id tristique. Curabitur id blandit nisl, sed dictum odio. Nullam pretium lacinia justo sed rhoncus. Nam neque purus, bibendum et dui quis, facilisis congue est. Praesent et neque sed dui convallis lobortis."
        }, {
            name: "Jack Sparrow",
            status: "Interview",
            comment: false
        }, {
            name: "Sergey Brin",
            status: "Offer",
            comment: false
        }

    ]


    function addComment(candidateSelected) {
        _.each($scope.dataSuggectionsSimulated, function(candidate) {

            if (candidate.name == candidateSelected) {
                candidate.addComment = true
                candidate.commentTemp = candidate.comment
                if (candidate.commentTemp == false) {
                    candidate.commentTemp = ""
                };
            };

        });
    }


    _.each($scope.dataSuggectionsSimulated, function(candidate) {

        if (candidate.status == 'Interview') {
            candidate.sortLevel = 1
        };
        if (candidate.status == 'Offer') {
            candidate.sortLevel = 2
        };
        if (candidate.status == 'Rejected') {
            candidate.sortLevel = 0
        };
        if (candidate.status == 'Hired') {
            candidate.sortLevel = 3
        };

    });

    $scope.addComment = addComment


});
