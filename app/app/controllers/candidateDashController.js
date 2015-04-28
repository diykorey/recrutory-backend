'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('candidateDash', function($scope, $rootScope, $log, ApiDataFactory, $timeout) {

    $scope.hidePredefined = false;
    $scope.hideCustom = true;
    $scope.hidePrime = true;
    $scope.customFields = []
    $scope.flowaddModel = {}
    $scope.addFieldProgress = false

    /**
     * Gets all candidates data from API
     */

    $scope.getCandidatesData = function() {
        $rootScope.loader = true
        ApiDataFactory.queryGet('candidate/findByOwner/1').then(function(result) {
            $rootScope.loader = false

            $scope.candidatesData = result // response data


        })
    }

    $scope.getCandidatesData()


    /**
     * Tags actions, add and delete.
     */


    $scope.tags = {

        add: function() {
            if (/^\s*$/.test($scope.tagEdit)) {
                $scope.tagEdit = ""
                return

            }

            _.each($scope.currentCandidate.tags, function(tag) {
                if (tag.keyword === $scope.tagEdit) {
                    $scope.sameTagFound = true
                };

            });

            if ($scope.sameTagFound === true) {
                $scope.tagEdit = ""
                delete $scope.sameTagFound
                return
            };


            $scope.tagEdit = $scope.tagEdit.replace(/,/g, "")
            $rootScope.updateProcess = true
            var tag = {}
            tag.keyword = $scope.tagEdit
            $scope.currentCandidate.tags.push(tag)
            tag = {}
            tag.tags = [$scope.tagEdit]
            var URL = 'candidate/' + $scope.currentCandidate.id + '/tags'
            ApiDataFactory.queryPost(URL, tag).then(function(result) {
                $rootScope.updateProcess = false
                $scope.showToast()
                $scope.currentCandidate = result
            })
            $scope.tagEdit = ""
        },
        delete: function(tag) {
            var i = 0
            _.each($scope.currentCandidate.tags, function(tagEach) {

                if (tagEach.keyword === tag.keyword) {
                    $scope.currentCandidate.tags.splice(i, 1);
                    var URL = 'candidate/' + $scope.currentCandidate.id + '/tags/' + tag.id
                    ApiDataFactory.queryDelete(URL).then(function(result) {})
                    return
                };
                i++
            })
        }

    }





    /**
     * Sets current candidate to scope
     */

    $scope.selectCandidate = function(candidate) {

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
        // window.scrollTo(0, 0);
        $scope.currentCandidate = candidate
        $timeout(function() {
            $scope.selectedIndex = 1
        }, 0);
    }

    /**
     * Note action class.
     * this add() - add new note to $scope.notes array, with ket new.
     * this discard() - removes last element from $scope.notes array, exactly latest new.
     * this save() - Remowing new key, and sending request to add new note, then gets new data with ID's and Date of creation set.
     * this edit() - PARAMS : note - clicked edit entity | Update note via this id and with new value.
     * this deleteNote() - PARAMS : note - clicked edit entity | Splices $scope.array and send API request ( callback getCandidateNotes() )  with note id to delete.
     */

    $scope.noteAction = {
        add: function() {
            function note() {
                this.new = true;
                this.placeholder = "Note text";

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
                ApiDataFactory.queryDelete(URL).then(function(result) {})
            };
            note.deleteNote = true
        },
        save: function() {
            $rootScope.updateProcess = true
            var note = $scope.notes[$scope.notes.length - 1]
            if (note.comment == "") {
                return
            };
            console.log(note)
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



    /**
     * New custom candidate field action class.
     * this add() - sets first step, and view is showing input to enter name of field
     * this addText() - sets second step, where use is able to set text for custom field
     * this save() - gets all entered data and sends generated data to API, replaces all local entities from API data.
     * this candel() - cancels new field creation process
     * this delete() - deletes current focused field ($scope.currentField)
     */

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
            newField.type.prime = $scope.newFieldPrime
            newField.fieldValue = $scope.newFieldValue


            delete $scope.addProgress
            $scope.newFieldName = ''
            $scope.newFieldPrime = ''
            $scope.newFieldValue = ''
            var URL = "candidate/" + $scope.currentCandidate.id + "/addfield"
            ApiDataFactory.queryPost(URL, newField).then(function(result) {
                $scope.candidatesData[_.indexOf($scope.candidatesData, result)] = result
                $scope.currentCandidate = result
                $rootScope.updateProcess = false
                $scope.showToast()
            })
        },
        cancel: function() {

            delete $scope.addProgress
            $scope.newFieldName = ''
            $scope.newFieldPrime = ''
            $scope.newFieldValue = ''
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
                        delete $scope.candidatesData[_.indexOf($scope.candidatesData, $scope.currentCandidate)].summaryCard.customFields[field.type.name]
                        delete $scope.currentField
                        $rootScope.updateProcess = false
                        $scope.showToast()
                    })
                }

            });
        }
    }


    /**
     * Card quick action class.
     * this main() - sets current candidate, and switches to MAIN side menu tab
     * this flow() - sets current candidate, and switches to FLOW side menu tab
     * this notes() - sets current candidate, and switches to NOTE side menu tab
     */


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


    /**
     * Gets current candidate notes [ usable for refresh also ]
     */

    function getCandidateNotes() {

        ApiDataFactory.queryGet('comment/candidate/' + $scope.currentCandidateData.id).then(function(result) {
            $scope.notes = result
            $rootScope.updateProcess = false
        });

    }

    /**
     * Gets currentCandidate suggestions
     */

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


    /**
     * Selected tab watcher
     */


    $scope.$watch('selectedIndex', function(indexNew, indexOld) {

        if (indexNew == 1) {
            ApiDataFactory.queryGet('workflow/byCandidate/' + $scope.currentCandidateData.id).then(function(result) {
                $rootScope.updateProcess = false;
                $scope.candidatesFlowData = result // response data
            });
        };
        if (indexNew == 3) {
            getCandidateNotes()
        };
        if (indexNew == 2) {
            $scope.suggestedPage = 0
            getSuggestions($scope.suggestedPage)
        };

    });

    /**
     * Automatically copy currentCandidate data to currentCandidatesData
     */

    $scope.$watch('currentCandidate', function(current, old) {
        $scope.currentCandidateData = angular.copy($scope.currentCandidate)
    }, true);



    /**
     * Sends selected flow data to API
     */

    $scope.sendFlow = function() {
        $rootScope.updateProcess = true
        var objToSend = {}
        objToSend.flows = $scope.flowData
        console.log(JSON.stringify(objToSend))
        ApiDataFactory.queryPost('workflow/', JSON.stringify(objToSend)).then(function(result) {
            $scope.selectedIndex = 1;
            $scope.flowaddModel = {}
            $rootScope.updateProcess = false
            $scope.showToast()
        })
    }

    /**
     * Watches new flow data selections
     */

    $scope.changeFlowData = function(vacancyId) {
        !$scope.flowaddModel[vacancyId]
        console.log($scope.flowaddModel)
        $scope.flowData = []
        _.each($scope.flowaddModel, function(state, vacancy) {
            if (state == true) {
                var vacancyEach = {}
                vacancyEach.candidateId = $scope.currentCandidate.id
                vacancyEach.vacancyId = parseInt(vacancy)
                $scope.flowData.push(vacancyEach)
            };
        });
    }


});
