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
        ApiDataFactory.queryGet('candidate/findByOwner/1').then(function(result) {
            $rootScope.loader = false

            $scope.candidatesData = result // response data

            console.log($scope.candidatesData)

        })
    }

    getData()

    function selectCandidate(candidateData) {
        $scope.currentCandidate = candidateData
        console.log($scope.currentCandidate)
        $log.info('Set candidate', $scope.currentCandidate);
    }


    // Update vacancy info
    function updateInfo(candidate) {
        var dataToSend = {}
        if ($scope.currentCandidateData.timelineRecords[0].fields[0].fieldValue != $scope.currentCandidate.timelineRecords[0].fields[0].fieldValue) {
            dataToSend.id = $scope.currentCandidateData.id
            dataToSend.createTime = $scope.currentCandidateData.createTime
            dataToSend.tags = $scope.currentCandidateData.tags
            dataToSend.timelineRecords = []
            dataToSend.timelineRecords.push($scope.currentCandidateData.timelineRecords[0])
            dataToSend.summaryCard = $scope.currentCandidateData.summaryCard
            var URL = 'candidate/update'
            ApiDataFactory.queryPost(URL, dataToSend).then(function(result) {
                $rootScope.updateProcess = false
                $scope.showToast()
            })



        }
        angular.copy($scope.currentCandidateData, $scope.currentCandidate);
    }


    //     {
    //   "id": 0,
    //   "createTime": "string",
    //   "tags": [
    //     {
    //       "id": 0,
    //       "keyword": "string"
    //     }
    //   ],
    //   "timelineRecords": [
    //     {
    //       "id": 0,
    //       "createTime": "string",
    //       "fields": [
    //         {
    //           "id": 0,
    //           "type": {},
    //           "fieldValue": "string"
    //         }
    //       ]
    //     }
    //   ],
    //   "summaryCard": {}
    // }


    $scope.updateInfo = updateInfo


    $scope.$watch('currentCandidate', function(current, old) {
        $scope.currentCandidateData = angular.copy($scope.currentCandidate)
        console.log($scope.currentCandidateData)
    }, true);


    $scope.tabsCandidateDetails = tabsCandidateDetails;

    $scope.selectCandidate = selectCandidate;

});
