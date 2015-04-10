'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('dashCtrl', function($scope, $rootScope, $log, ApiDataFactory, $http, $timeout) {

    $scope.disableSuggestions = true
    $scope.actionsVal = {}
    $scope.flowData = {}
    $scope.actionsData = {}
    $scope.flowaddModel = {}


    function getData() {
        $rootScope.loader = true
        ApiDataFactory.queryGet('vacancy/byState/open').then(function(result) {
            $rootScope.loader = false

            $scope.vacanciesData = result // response data
        })
    }

    getData()

    var tabsVacancies = [{
        heading: 'Main',
    }, {
        heading: 'Flow',

    }, {
        heading: 'Suggestions',

    }, {
        heading: 'Notes',

    }];

    $scope.tabsVacancies = tabsVacancies;

    $scope.candidateActions = [{
        name: "INIT"
    }, {
        name: "CONTACT"
    }, {
        name: "INTERVIEW"
    }, {
        name: "OFFER"
    }, {
        name: "HIRED"
    }, {
        name: "REJECTED"
    }]

    $scope.fastActions = [{
        name: "Add vacancy",
        index: 2
    }]

    function fastAction(vacancy, index) {

        selectVacancy(vacancy)

        $timeout(function() {
            $scope.selectedIndex = index
        }, 0);
    }


    function addTag() {


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
            $scope.currentVacancyData = $scope.currentVacancy
        })



        $scope.tagEdit = ""
    }

    function selectVacancy(vacancy) {
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




    function removeTag(tagToDelete) {
        var i = 0
        _.each($scope.currentVacancy.tags, function(tag) {

            if (tag.keyword === tagToDelete) {
                $scope.currentVacancyData.tags.splice(i, 1);
                var URL = 'vacancy/' + $scope.currentVacancy.id + '/tags/' + tag.id
                ApiDataFactory.queryDelete(URL).then(function(result) {})
                return
            };
            i++
        });

        angular.copy($scope.currentVacancyData.tags, $scope.currentVacancy.tags);

    }

    function quickAction(action, vacancy, index) {
        $scope.returnSelectDefault = true
        var i = -1

        if (action == 'select') {
            $scope.currentVacancy = vacancy
            $timeout(function() {
                $scope.selectedIndex = index
            }, 0);


        }
        if (action == 'archive' || action == 'unarchive') {



            _.each($scope.vacanciesData, function(vacancyEach) {
                i++

                vacancyEach.allowArchive = false;


                if (vacancy.id === vacancyEach.id) {

                    if (action == 'unarchive') {
                        $scope.vacanciesData[i].allowArchive = false;
                    }
                    if (action == 'archive') {
                        $scope.vacanciesData[i].allowArchive = true;
                    };

                    var iterator = i


                    return
                };

            });
        };
    }

    $scope.quickAction = quickAction


    function archiveVacancy(vacancy) {

        $scope.loader = true
        delete $scope.currentVacancy
        var URL = 'vacancy/archive/' + vacancy.id
            // ApiDataFactory.queryDelete(URL).then(function(result) {
            //     $scope.loader = true;
            //     
            // })
        var i = 0;
        _.each($scope.vacanciesData, function(vacancyEach) {
            if (vacancy.id == vacancyEach.id) {

                var iterator = i

                $scope.vacanciesData.splice(i, 1);
                $scope.loader = false
                $scope.returnSelectDefault = false
                $scope.currentVacancy = false
            };

            i++

        });

    }

    $scope.archiveVacancy = archiveVacancy


    function sendFlow() {
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

    function updateInfo(vacancyId) {
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



    function editDescription
        (candidateSelected) {
            if ($scope.currentCommentEditId == candidateSelected) {
                delete $scope.currentCommentEditId;
                return
            };
            $scope.currentCommentEditId = candidateSelected
        }

    $scope.$watch('currentVacancy', function(current, old) {
        $scope.flowaddModel = {}
        $scope.currentVacancyData = angular.copy($scope.currentVacancy)
        $scope.selectedIndex = 0;
    }, true);

    $scope.$watch('filterValue', function(newVal, oldVal) {
        $scope.currentVacancy = false;
    });


    $scope.$watch('actionsVal', function onScopeChange(newCol, oldCol) {
        var changedVal = _.omit(newCol, function(v, k) {
            return oldCol[k] === v;
        });
    }, true);

    $scope.$watch('flowaddModel', function onScopeChange(newCol, oldCol) {
        var changedVal = _.omit(newCol, function(v, k) {
            return oldCol[k] === v;
        });

        $scope.flowData = []
        _.each($scope.flowaddModel, function(state, candidate) {

            if (state == true) {
                $scope.candidateEach = {}
                $scope.candidateEach.candidateId = parseInt(candidate)
                $scope.candidateEach.vacancyId = $scope.currentVacancy.id
                $scope.flowData.push($scope.candidateEach)
            };

        });
        console.log($scope.flowData)
    }, true);

    $scope.$watch('actionsData', function(current, old) {}, true);

    $scope.$watch('selectedIndex', function(indexNew, indexOld) {
        if (indexNew != 2) {
            $scope.suggestionPage = 0
        };
        if (indexNew == 1) {
            $rootScope.updateProcess = true
            ApiDataFactory.queryGet('workflow/byVacancy/' + $scope.currentVacancyData.id).then(function(result) {
                $rootScope.updateProcess = false
                $scope.vacanciesFlowData = result // response data
            });

        };
        if (indexNew == 2) {
            getSuggestions(-1)

        };
    });



    function getSuggestions(page) {
        $scope.suggestionPage = page
        $scope.suggestionPage++;
        $rootScope.updateProcess = true
        ApiDataFactory.queryGet('candidate/findRecommended?vacancyId=' + $scope.currentVacancy.id + '&page=' + $scope.suggestionPage + '&size=5').then(function(result) {
            if ($scope.suggestionPage > 0) {
                _.each(result, function(candidate) {
                    $scope.flowRecommended.push(candidate)

                });

            } else {
                $scope.flowRecommended = result;
            }

            $rootScope.updateProcess = false
        });
    }

    $scope.getSuggestions = getSuggestions

    $scope.updateInfo = updateInfo
    $scope.addTag = addTag
    $scope.fastAction = fastAction
    $scope.isEmpty = isEmpty
    $scope.removeTag = removeTag;
    $scope.selectVacancy = selectVacancy;
    $scope.editDescription = editDescription;
    $scope.sendFlow = sendFlow
});
