'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('NavCtrl', function ($scope, $location) {
 var tabs = [
      {
      heading: 'Dashboard',
      route:   'dashboard'
    },
    {
      heading: 'Vacancies list',
      route:   'list'
    },
      {
          heading: 'Candidate Dashboard',
          route:   'candidateDashboard'
      },
      {
          heading: 'Candidate List',
          route:   'candidateList'
      }
    ];

    $scope.tabs = tabs;

   
function navigate(view){
     
        }

        $scope.navigate = navigate
  });
