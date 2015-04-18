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


    $scope.customField = {
        add: function() {
            delete $scope.currentField
            $scope.addFieldProgress = 1
        },
        addText: function() {
            $scope.addFieldProgress = 2
        },
        save: function() {
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

                var i = 0
                _.each($scope.candidatesData, function(candidate) {
                    if (candidate.id == result.id) {
                        $scope.candidatesData[i] = result;
                        $scope.currentCandidate = result;
                    }
                    i++
                });

                $rootScope.updateProcess = false
                $scope.showToast()
            })
        },
        cancel: function() {
            delete $scope.addFieldProgress
        },
        set: function(field) {
            $scope.currentField = field
            console.log($scope.currentField.type.prime)
        },
        unset: function() {
            delete $scope.currentField
        },
        delete: function() {
            $rootScope.updateProcess = true

            var i = 0;
            _.each($scope.currentCandidate.summaryCard.customFields, function(field) {
                if (field.id == $scope.currentField.id) {
                    $scope.customFields.splice(i, 1)
                    var URL = 'candidate/removefield/' + field.id
                    ApiDataFactory.queryDelete(URL).then(function(result) {
                        var i = 0
                        _.each($scope.candidatesData, function(candidate) {
                            if (candidate.id == $scope.currentCandidate.id) {
                                delete $scope.candidatesData[i].summaryCard.customFields[field.type.name]
                                delete $scope.currentField
                                $rootScope.updateProcess = false
                                $scope.showToast()
                            };
                            i++

                        });
                    })
                }
                i++
            });
        }
    }



    $scope.updateInfo = updateInfo


    $scope.$watch('currentCandidate', function(current, old) {
        $scope.currentCandidateData = angular.copy($scope.currentCandidate)
        console.log($scope.currentCandidateData)
        $scope.customFields = []
        $scope.predefinedFields = []
        if (typeof $scope.currentCandidateData.summaryCard !== "undefined") {
            _.each($scope.currentCandidateData.summaryCard.customFields, function(value, name) {
                console.log(value)
                $scope.customFields.push(value)
            });
            _.each($scope.currentCandidateData.summaryCard.predefinedFields, function(value, name) {
                console.log(value)
                $scope.predefinedFields.push(value)
            });

        };

    }, true);


    $scope.tabsCandidateDetails = tabsCandidateDetails;

    $scope.selectCandidate = selectCandidate;

});
