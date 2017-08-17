var chatApp = angular.module('chat', ['ngRoute']);

chatApp.config(function ($routeProvider) {
    $routeProvider
        .when('/messages',
            {
             controller: 'AllMessagesController',
             templateURL: '/ViewAllMessages.html'
            })
        .otherwise( {redirectTo: '/'});
});

chatApp.service('messageService', function(){
   var message = {};

   var addMessage = function (v) {
       message = v;
   };
   var getMessage = function(){
       return message;
   };

   return {
       addMessage: addMessage,
       getMessage: getMessage
   };

});

chatApp.controller('AllMessagesController', function($scope, $window, $http){
  $scope.transfer = {};
   $scope.error = false;
   console.log("All message controller");
   $http
       .get('/api/message/all')
       .then(function(response) {
           $scope.messages = response.data;
       });
});