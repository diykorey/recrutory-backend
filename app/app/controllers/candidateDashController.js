'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('candidateDash', function($scope, $rootScope, $log, ApiDataFactory, $timeout) {


    $scope.customFields = []
    $scope.flowaddModel = {}


    var tabsCandidateDetails = [{
        heading: 'Main',
    }, {
        heading: 'Flow',

    }, {
        heading: 'Suggestions',
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

    function selectCandidate(candidate) {

        document.title = "Recrutory - " + candidate.name;
        if ($scope.returnSelectDefault == true) {
            $scope.returnSelectDefault = false

            return
        };
        if ($scope.currentCandidate && candidate.id == $scope.currentCandidate.id) {
            $scope.currentCandidate = false;
            document.title = "Recrutory"
            return
        };
        window.scrollTo(0, 0);
        $scope.currentCandidate = candidate
        $timeout(function() {
            $scope.selectedIndex = 1
        }, 0);
    }




    // Update Candidate info
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

    $scope.noteAction = {
        add: function() {
            function note() {
                this.new = true;
                this.placeholder = "Note text";
                this.creationDate = new Date();
            }
            $scope.notes.push(new note())
        },
        discard: function() {
            $scope.notes.splice($scope.notes.length - 1, 1)
        },
        edit: function(note) {
            $rootScope.updateProcess = true

            note.entityId = note.id
            note.type = 'CANDIDATE'
            note.text = note.comment
            URL = 'comment/' + note.id
            ApiDataFactory.queryPost(URL, note).then(function(result) {
                delete note.editNote
                $rootScope.updateProcess = false
                $scope.showToast()


            })
        },
        deletenote: function(note) {
            if (note.deleteNote) {
                $scope.notes.splice(_.indexOf($scope.notes, note), 1)
                var URL = 'comment/' + note.id
                    // ApiDataFactory.queryDelete(URL).then(function(result) {})
            };
            note.deleteNote = true
        },
        save: function() {
            $rootScope.updateProcess = true
            var note = $scope.notes[$scope.notes.length - 1]
            if (note.comment == "") {
                return
            };
            delete note.placeholder
            delete note.new
            delete note.creationDate
            note.entityId = $scope.currentCandidate.id
            note.type = 'CANDIDATE'
            note.text = note.comment
            delete note.comment

            var URL = "comment"
            ApiDataFactory.queryPost(URL, note).then(function(result) {

                getCandidateNotes()

            })
        }
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
        },
        unset: function() {
            delete $scope.currentField
        },
        delete: function() {
            $rootScope.updateProcess = true


            _.each($scope.currentCandidate.summaryCard.customFields, function(field) {
                if (field.id == $scope.currentField.id) {
                    $scope.customFields.splice(_.indexOf($scope.customFields, $scope.currentField), 1)
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

            });
        }
    }



    $scope.quickAction = {
        main: function(candidate) {
            $scope.returnSelectDefault = true
            $scope.currentCandidate = candidate
            $timeout(function() {
                $scope.selectedIndex = 0
            }, 0);

        },
        flow: function(candidate) {
            $scope.returnSelectDefault = true
            $scope.currentCandidate = candidate
            $timeout(function() {
                $scope.selectedIndex = 1
            }, 0);

        },
        notes: function(candidate) {
            $scope.returnSelectDefault = true
            $scope.currentCandidate = candidate

            $timeout(function() {
                $scope.selectedIndex = 3
            }, 0);

        }
    }



    $scope.updateInfo = updateInfo

    function getCandidateNotes() {

        ApiDataFactory.queryGet('comment/candidate/' + $scope.currentCandidateData.id).then(function(result) {
            $scope.notes = result
            $rootScope.updateProcess = false
        });
    }


    // Selected detail editor tab watcher
    $scope.$watch('selectedIndex', function(indexNew, indexOld) {

        if (indexNew == 1) {
            ApiDataFactory.queryGet('workflow/byCandidate
/' + $scope.currentCandidateData.id).then(function(result) {
                $rootScope.updateProcess = false
                $scope.candidatesFlowData = result // response data
            });
        };
        if (indexNew == 3) {
            getCandidateNotes()
        };
        if (indexNew == 2) {
            getSuggestions(0)
        };
        if (indexNew != 2) {
            $scope.suggestedPage = 0
            $scope.availablePage = 0
        };
    });


    $scope.$watch('currentCandidate', function(current, old) {
        $scope.currentCandidateData = angular.copy($scope.currentCandidate)
        $scope.customFields = []
        $scope.predefinedFields = []


    }, true);

    // Send selected flow data
    $scope.sendFlow = function() {
        $rootScope.updateProcess = true
        var objToSend = {}
        objToSend.flows = $scope.flowData
        console.log(JSON.stringify(objToSend))
        ApiDataFactory.queryPost('workflow/', JSON.stringify(objToSend)).then(function(result) {
            $scope.selectedIndex = 1;
            $scope.flowaddModel = {}
            getSuggestions()
            $rootScope.updateProcess = false
            $scope.showToast()
        })
    }

    $scope.$watch('flowaddModel', function onScopeChange(newCol, oldCol) {

        var changedVal = _.omit(newCol, function(v, k) {
            return oldCol[k] === v;
        });

        $scope.flowData = []
        _.each($scope.flowaddModel, function(state, vacancy) {
            console.log(vacancy, state)
            if (state == true) {
                var vacancyEach = {}
                vacancyEach.candidateId = $scope.currentCandidate.id
                vacancyEach.vacancyId = parseInt(vacancy)

                $scope.flowData.push(vacancyEach)
            };

        });
        console.log($scope.flowData)
    }, true);


    function getSuggestions(pageSuggested) {


        $rootScope.updateProcess = true
        $scope.suggestedPage = pageSuggested

        ApiDataFactory.queryGet('vacancy/findRecommended?candidateId=' + $scope.currentCandidate.id + '&page=' + $scope.suggestedPage + '&size=5').then(function(result) {
            if ($scope.suggestedPage > 0) {
                _.each(result, function(candidate) {
                    $scope.flowSuggested.push(candidate)

                });

            } else {
                $scope.flowSuggested = result;
            }
            $rootScope.updateProcess = false
            $scope.suggestedPage++;
        });
    }


    $scope.tabsCandidateDetails = tabsCandidateDetails;

    $scope.selectCandidate = selectCandidate;

});
