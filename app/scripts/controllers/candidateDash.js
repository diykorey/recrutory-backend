'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('candidateDash', function ($scope, $log, ApiDataFactory) {


function getData() {
        ApiDataFactory.query('http://recrutory-web-dev.cloudapp.net:8080/recrutory/candidate/findByOwner/1').then(function(result) {


  

            $scope.candidatesData = result // response data
            console.log($scope.candidatesData)

        })
    }
    getData()

function selectCandidate(candidateData){
console.log(candidateData)
}

$scope.selectCandidate = selectCandidate;

  });
