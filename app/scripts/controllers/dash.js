'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('dashCtrl', function ($scope, $rootScope, $log, ApiDataFactory) {





function getData() {
$rootScope.loader = true
        ApiDataFactory.query('http://recrutory-web-dev.cloudapp.net:8080/recrutory/vacancy/byState/open').then(function(result) {
$rootScope.loader = false

            $scope.vacanciesData = result // response data
console.log(result)
        })
    }
    getData()



var columnsTable = [
    {
      heading: 'title'
    },
    {
      heading: 'status'

    },
    {
      heading: 'createtime'

    },
      
      {
          heading: 'tags'

      }
      ,
      {
          heading: 'notifications'

      }
    ];

    $scope.columnsTable =  columnsTable;


  });

		 		