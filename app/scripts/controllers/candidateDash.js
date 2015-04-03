'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('candidateDash', function($scope, $rootScope, $log, ApiDataFactory) {

    $scope.currentCandidate = false

    var tabsCandidateDetails = [{
        heading: 'Main',
    }, {
        heading: 'Flow',

    }, {
        heading: 'Notes',

    }];

    function getData() {
        $rootScope.loader = true
        ApiDataFactory.queryGet('http://recrutory-web-dev.cloudapp.net:8080/recrutory/candidate/findByOwner/1').then(function(result) {
            $rootScope.loader = false

            $scope.candidatesData = result // response data

            console.log($scope.candidatesData)

        })
    }

    getData()

    function selectCandidate(candidateData) {
        $scope.currentCandidate = candidateData
        $log.info('Set candidate', $scope.currentCandidate);
    }

    function addCandidateTags() {
        var tagEach = {};
        tagEach.keyword = $scope.tagInput;
        $scope.currentCandidateTags.push(tagEach)
    }

    function removeTag(tagToDelete) {
        var i = -1
        _.each($scope.currentCandidateData.tags, function(tag) {
            i++
            if (tag.keyword == tagToDelete) {
                $scope.currentCandidateData.tags.splice(i, 1);
                return
            };

        });
    }

    $scope.$watch('currentCandidate', function(current, old) {
        $scope.currentCandidateData = angular.copy($scope.currentCandidate)
    }, true);

    $scope.selectedIndex = 2;
    $scope.tabsCandidateDetails = tabsCandidateDetails;
    $scope.addCandidateTags = addCandidateTags;
    $scope.removeTag = removeTag;
    $scope.selectCandidate = selectCandidate;

});
