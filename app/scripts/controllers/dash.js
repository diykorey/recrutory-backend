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


    function getData() {
        $rootScope.loader = true
        ApiDataFactory.queryGet('http://recrutory-web-dev.cloudapp.net:8080/recrutory/vacancy/byState/open').then(function(result) {
            $rootScope.loader = false

            $scope.vacanciesData = result // response data
            console.log(result)
        })
    }

    getData()

    var tabsVacancies = [{
        heading: 'Main',
    }, {
        heading: 'Vacancies',

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

    function selectVacancy(vacancy) {
        if (vacancy.id == $scope.currentVacancy.id) {
            $scope.currentVacancy = false;
            return
        };
        $scope.currentVacancy = vacancy
    }





    function removeTag(tagToDelete) {
        var i = -1
        _.each($scope.currentVacancyData.tags, function(tag) {
            i++
            if (tag.keyword == tagToDelete) {
                $scope.currentVacancyData.tags.splice(i, 1);
                return
            };

        });
    }

    function updateInfo(vacancyId) {
        var dataToSend = {}
        dataToSend.name = $scope.currentVacancyData.name
        dataToSend.requirements = $scope.currentVacancyData.requirements
        $rootScope.updateProcess = true
        var URL = 'http://recrutory-web-dev.cloudapp.net:8080/recrutory/vacancy/' + vacancyId
        ApiDataFactory.queryPost(URL, dataToSend).then(function(result) {
            $rootScope.updateProcess = false
            $scope.showToast()
        })
    }


    function editComment(candidateSelected) {
        $scope.currentCommentEditId = candidateSelected
    }

    $scope.$watch('currentVacancy', function(current, old) {
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

    $scope.$watch('actionsData', function(current, old) {
        console.log($scope.actionsData)
    }, true);

    $scope.$watch('selectedIndex', function(indexNew, indexOld) {
        if (indexNew == 1) {
            $rootScope.updateProcess = true
            ApiDataFactory.queryGet('http://recrutory-web-dev.cloudapp.net:8080/recrutory/workflow/byVacancy/' + $scope.currentVacancyData.id).then(function(result) {
                $rootScope.updateProcess = false
                $scope.vacanciesFlowData = result // response data
                console.log(result)
            });

        };
        if (indexNew == 2) {
            $rootScope.updateProcess = true
            ApiDataFactory.queryGet('http://recrutory-web-dev.cloudapp.net:8080/recrutory/workflow/byVacancy/' + $scope.currentVacancyData.id).then(function(result) {
                $rootScope.updateProcess = false
                $scope.vacanciesFlowData = result // response data
                console.log(result)
            });

        };
    });


    $scope.updateInfo = updateInfo
    $scope.fastAction = fastAction
    $scope.isEmpty = isEmpty
    $scope.removeTag = removeTag;
    $scope.selectVacancy = selectVacancy;
    $scope.editComment = editComment;
});
