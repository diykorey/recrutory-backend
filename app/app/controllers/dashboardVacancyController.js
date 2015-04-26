'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('dashCtrl', function($scope, $rootScope, $log, ApiDataFactory, $http, $timeout) {

    // Init dashCtrl scope values
    $scope.disableSuggestions = true
    $scope.actionsVal = {}
    $scope.flowData = {}
    $scope.actionsData = {}
    $scope.flowaddModel = {}

    /**
     * Gets all vacancies data from API
     */

    $scope.getVacancyData = function() {
        $rootScope.loader = true
        ApiDataFactory.queryGet('vacancy/byState/open').then(function(result) {
            $rootScope.loader = false
            $scope.vacanciesData = result // response data
        })
    }

    $scope.getVacancyData();


    /**
     * Tags actions, add and delete.
     */


    $scope.tags = {

        add: function() {
            if (/^\s*$/.test($scope.tagEdit)) {
                $scope.tagEdit = ""
                return

            }

            _.each($scope.currentVacancy.tags, function(tag) {
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
            $scope.currentVacancy.tags.push(tag)
            tag = {}
            tag.tags = [$scope.tagEdit]
            var URL = 'vacancy/' + $scope.currentVacancy.id + '/tags'
            ApiDataFactory.queryPost(URL, tag).then(function(result) {
                $rootScope.updateProcess = false
                $scope.showToast()
                $scope.currentVacancy = result
            })
            $scope.tagEdit = ""
        },
        delete: function(tag) {
            var i = 0
            _.each($scope.currentVacancy.tags, function(tagEach) {

                if (tagEach.keyword === tag.keyword) {
                    $scope.currentVacancy.tags.splice(i, 1);
                    var URL = 'vacancy/' + $scope.currentVacancy.id + '/tags/' + tag.id
                    ApiDataFactory.queryDelete(URL).then(function(result) {})
                    return
                };
                i++
            })
        }

    }

    /**
     * Sets current vacancy
     */

    $scope.selectVacancy = function(vacancy) {
        document.title = "Recrutory - " + vacancy.name;

        if ($scope.returnSelectDefault == true) {
            $scope.returnSelectDefault = false

            return
        };
        if (vacancy.id === $scope.currentVacancy.id && $scope.currentVacancy) {
            $scope.currentVacancy = false;
            document.title = "Recrutory"
            return
        };
        window.scrollTo(0, 0);
        $scope.currentVacancy = vacancy
        $timeout(function() {
            $scope.selectedIndex = 1
        }, 0);
    }





    /**
     * Card quick action class.
     * this main() - sets current vacancy, and switches to MAIN side menu tab
     * this flow() - sets current vacancy, and switches to FLOW side menu tab
     * this notes() - sets current vacancy, and switches to NOTE side menu tab
     */

    $scope.quickAction = {
        main: function(vacancy) {
            $scope.returnSelectDefault = true
            $scope.currentVacancy = vacancy
            $timeout(function() {
                $scope.selectedIndex = 0
            }, 0);

        },
        flow: function(vacancy) {
            $scope.returnSelectDefault = true
            $scope.currentVacancy = vacancy
            $timeout(function() {
                $scope.selectedIndex = 1
            }, 0);

        },
        notes: function(vacancy) {
            $scope.returnSelectDefault = true
            $scope.currentVacancy = vacancy

            $timeout(function() {
                $scope.selectedIndex = 3
            }, 0);

        },
        archive: function(vacancy, type) {
            $scope.returnSelectDefault = true
            var i = 0
            _.each($scope.vacanciesData, function(vacancyEach) {


                if (vacancy.id === vacancyEach.id) {

                    if (type == 'unarchive') {
                        $scope.vacanciesData[i].allowArchive = false;
                    }
                    if (type == 'archive') {
                        $scope.vacanciesData[i].allowArchive = true;
                    };




                    return
                };
                i++
            });
        }
    }




    // Archive vacancy
    $scope.archiveVacancy = function(vacancy) {

        $scope.loader = true
        delete $scope.currentVacancy
        var URL = 'vacancy/archive/' + vacancy.id
            // ApiDataFactory.queryDelete(URL).then(function(result) {
            //     $scope.loader = true;
            //     
            // })
        var i = 0;
        $scope.vacanciesData.splice(_.indexOf($scope.vacanciesData, vacancy), 1);
        $scope.loader = false
        $scope.returnSelectDefault = false
        $scope.currentVacancy = false

    }





    /**
     * Sends selected flow data to API
     */

    $scope.sendFlow = function() {
        $rootScope.updateProcess = true
        var objToSend = {}
        objToSend.flows = $scope.flowData
        ApiDataFactory.queryPost('workflow/', objToSend).then(function(result) {
            $scope.selectedIndex = 1;
            $scope.flowaddModel = {}
            getSuggestions()
            $rootScope.updateProcess = false
            $scope.showToast()
        })
    }



    function getVacancyNotes() {

        ApiDataFactory.queryGet('comment/vacancy/' + $scope.currentVacancyData.id).then(function(result) {
            $scope.notes = result
            $rootScope.updateProcess = false
        });
    }


    /**
     * Note action class.
     * this add() - add new note to $scope.notes array, with ket new.
     * this discard() - removes last element from $scope.notes array, exactly latest new.
     * this save() - Remowing new key, and sending request to add new note, then gets new data with ID's and Date of creation set.
     * this edit() - PARAMS : note - clicked edit entity | Update note via this id and with new value.
     * this deleteNote() - PARAMS : note - clicked edit entity | Splices $scope.array and send API request ( callback getCandidate
Notes() )  with note id to delete.
     */

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
            note.type = 'VACANCY'
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

            console.log(note)
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
            note.entityId = $scope.currentVacancy.id
            note.type = 'VACANCY'
            note.text = note.comment
            delete note.comment
            console.log(note)

            var URL = "comment"
            ApiDataFactory.queryPost(URL, note).then(function(result) {

                getVacancyNotes()
                $rootScope.updateProcess = false
                $scope.showToast()
            })
            console.log(note)
        }
    }



    /**
     * Update vacancy info, API request
     */

    $scope.updateInfo = function(vacancyId) {
        var dataToSend = {}
        if ($scope.currentVacancyData.name != $scope.currentVacancy.name || $scope.currentVacancyData.requirements != $scope.currentVacancy.requirements) {
            dataToSend.name = $scope.currentVacancyData.name
            dataToSend.requirements = $scope.currentVacancyData.requirements
            $rootScope.updateProcess = true
            var URL = 'vacancy/' + vacancyId
            ApiDataFactory.queryPost(URL, dataToSend).then(function(result) {
                $rootScope.updateProcess = false
                $scope.showToast()
            })



        }
        angular.copy($scope.currentVacancyData, $scope.currentVacancy);
    }


    $scope.$watch('currentVacancy', function(current, old) {
        $scope.flowaddModel = {}
        $scope.currentVacancyData = angular.copy($scope.currentVacancy)
        $scope.selectedIndex = 0;
    }, true);

    $scope.$watch('filterValue', function(newVal, oldVal) {
        $scope.currentVacancy = false;
    });


    /**
     * Changes new flow data selections
     */

    $scope.changeFlowData = function(candidateId) {
        !$scope.flowaddModel[candidateId]
        console.log($scope.flowaddModel)
        $scope.flowData = []
        _.each($scope.flowaddModel, function(state, candidate) {
            if (state == true) {
                var candidateEach = {}
                candidateEach.vacancyId = $scope.currentVacancy.id
                candidateEach.candidateId = parseInt(candidate)
                $scope.flowData.push(candidateEach)
            };
        });
    }


    /**
     * Selected tab watcher
     */

    $scope.$watch('selectedIndex', function(indexNew, indexOld) {

        if (indexNew == 1) {
            $rootScope.updateProcess = true
            ApiDataFactory.queryGet('workflow/byVacancy/' + $scope.currentVacancyData.id).then(function(result) {
                $rootScope.updateProcess = false
                $scope.vacanciesFlowData = result // response data
            });
        };
        if (indexNew == 2) {
            $scope.getSuggestions(-1, -1, 'all')
        };
        if (indexNew == 3) {
            getVacancyNotes()
        };


        if (indexNew != 2) {
            $scope.suggestedPage = 0
            $scope.availablePage = 0
        };
    });


    /**
     * Gets currentVacancy suggestions
     */


    $scope.getSuggestions = function(pageSuggested, availablePage, type) {


        if (type == 'available' || type == 'all') {
            $rootScope.updateProcess = true
            $scope.availablePage = availablePage
            $scope.availablePage++;
            ApiDataFactory.queryGet('candidate/findAvailable?vacancyId=' + $scope.currentVacancy.id + '&page=' + $scope.availablePage + '&size=5').then(function(result) {
                if ($scope.availablePage > 0) {
                    _.each(result, function(vacancy) {
                        $scope.flowAvailable.push(vacancy)

                    });

                } else {
                    $scope.flowAvailable = result;
                }
                $rootScope.updateProcess = false

            });
        };


        if (type == 'suggested' || type == 'all') {
            $rootScope.updateProcess = true
            $scope.suggestedPage = pageSuggested
            $scope.suggestedPage++;
            ApiDataFactory.queryGet('candidate/findRecommended?vacancyId=' + $scope.currentVacancy.id + '&page=' + $scope.suggestedPage + '&size=5').then(function(result) {
                if ($scope.suggestedPage > 0) {
                    _.each(result, function(vacancy) {
                        $scope.flowSuggested.push(vacancy)

                    });

                } else {
                    $scope.flowSuggested = result;
                }
                $rootScope.updateProcess = false
            });
        };
    }

    $scope.isEmpty = isEmpty;

});
