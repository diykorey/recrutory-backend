'use strict';

/**
 * @ngdoc function
 * @name kandidatoApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the kandidatoApp
 */
kandidatoApp.controller('NavCtrl', function ($scope, $rootScope, $location) {
 var tabs = [
    
    {
      heading: 'Dashboard',
         type: 'link',
      route:   'dashboard'
    },
    {
      heading: 'Vacancies list',
         type: 'link',
      route:   'list'
    },
      {
          heading: 'Candidate Dashboard',
             type: 'link',
          route:   'candidateDashboard'
      },
      {
          heading: 'Candidate List',
             type: 'link',
          route:   'candidateList'
      }
    ];

    $scope.tabs = tabs;

   
function navigate(view){
     
        }

        $scope.navigate = navigate

  });


