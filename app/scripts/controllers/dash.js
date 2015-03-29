'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('dashCtrl', function($scope, $rootScope, $log, ApiDataFactory) {



    function getData() {
        $rootScope.loader = true
        ApiDataFactory.query('http://recrutory-web-dev.cloudapp.net:8080/recrutory/vacancy/byState/open').then(function(result) {
            $rootScope.loader = false

            $scope.vacanciesData = result // response data
            console.log(result)
        })
    }

    getData()

    var tabsVacancies = [{
        heading: 'Main',
    }, {
        heading: 'Flows',

    }, {
        heading: 'Suggestions',

    }, {
        heading: 'Notes',

    }];


    $scope.tabsVacancies = tabsVacancies;



    $scope.currentVacancy = false;

    function selectVacancy(vacancy) {
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

    $scope.$watch('currentVacancy', function(current, old) {
        $scope.currentVacancyData = angular.copy($scope.currentVacancy)
    }, true);

    $scope.$watch('filterValue', function(newVal, oldVal) {
        $scope.currentVacancy = false;
    });



    $scope.removeTag = removeTag;
    $scope.selectVacancy = selectVacancy;

});
