
kandidatoApp.directive('tagList', function() {
  return {
      restrict: 'E',
      replace: 'true',
      templateUrl: 'app/shared/tagList/tagList.html',
       scope: {
      tags: '='
    },
    link: function(scope, element) {
    }
  };
});