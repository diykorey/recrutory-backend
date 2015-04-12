'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('candidateDetails', function($scope, $log, ApiDataFactory) {
    var tabsCandidateDetails = [{
        heading: 'Main',
    }, {
        heading: 'Flow',

    }, {
        heading: 'Notes',

    }];

    function getCandidateTags(tags) {
        $scope.currentCandidateTags = []
        _.each(tags, function(tag) {
            var tagEach = {};
            tagEach.keyword = tag.keyword;
            $scope.currentCandidateTags.push(tagEach)
        });
    }

    function addCandidateTags() {
        var tagEach = {};
        tagEach.keyword = $scope.tagInput;
        $scope.currentCandidateTags.push(tagEach)
    }

    $scope.selectedIndex = 2;

    $scope.$watch('currentCandidate', function(current, old) {
        $scope.currentCandidateData = angular.copy($scope.currentCandidate)
        getCandidateTags($scope.currentCandidate.tags)

    }, true);


    $scope.tabsCandidateDetails = tabsCandidateDetails;
    $scope.addCandidateTags = addCandidateTags;

});
