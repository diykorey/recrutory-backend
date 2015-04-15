'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('candidateDash', function($scope, $rootScope, $log, ApiDataFactory) {


    $scope.customFields = []


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

    $scope.addFieldProgress = false

    function addCustomField(action) {
        if (action == 'add') {
            $scope.addFieldProgress = 1
        };
        if (action == 'addText') {
            $scope.addFieldProgress = 2
        };
        if (action == 'save') {
            $rootScope.updateProcess = true
            var newField = {}
            newField.type = {}
            newField.type.name = $scope.newFieldName
            newField.type.prime = false
            newField.fieldValue = $scope.newFieldValue
            $scope.customFields.push(newField)
            delete $scope.addFieldProgress
            $scope.newFieldName = "";
            $scope.newFieldValue = "";
            console.log(newField)
            var URL = "candidate/" + $scope.currentCandidate.id + "/addfield"
            ApiDataFactory.queryPost(URL, newField).then(function(result) {
                $rootScope.updateProcess = false
                $scope.showToast()
            })

        };
        if (action == 'cancel') {
            delete $scope.addFieldProgress
        };


    }

    $scope.addCustomField = addCustomField





    $scope.updateInfo = updateInfo


    $scope.$watch('currentCandidate', function(current, old) {
        $scope.currentCandidateData = angular.copy($scope.currentCandidate)
        console.log($scope.currentCandidateData)
        $scope.customFields = []
        if (typeof $scope.currentCandidateData.summaryCard !== "undefined") {
            _.each($scope.currentCandidateData.summaryCard.customFields, function(value, name) {
                $scope.customFields.push(value)
            });
        };

    }, true);


    $scope.tabsCandidateDetails = tabsCandidateDetails;

    $scope.selectCandidate = selectCandidate;

});
