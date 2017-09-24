var chat = angular.module('chat', ['ngRoute', 'smart-table','angularUtils.directives.dirPagination']);

chat.config(function ($routeProvider) {
    $routeProvider
        .when('/messages',
            {
                controller: 'MessagesController',
                templateUrl: 'messages.html'
            })
        .when('/login',{
            controller: 'LoginController',
            templateUrl: 'login.html'
        })
        .when('/register',{
            controller: 'RegisterController',
            templateUrl: 'register.html'
        })
        .otherwise({redirectTo: '/'});
});

chat.service('messageService', function () {

    var message = {};

    var addMessage = function (v) {
        message = v;
    };
    var getMessage = function () {
        return message;
    };

    return {
        addMessage: addMessage,
        getMessage: getMessage
    };

});


chat.controller('LoginController',function () {

    console.log("something");
});

chat.controller('RegisterController',function ($scope) {
    $scope.transfer = {};
    $scope.register = function () {
        $http.post('api/clients/create',{
                "email": "",
                "firstName": $scope.firstName,
                "lastName": $scope.lastName,
                "login": $scope.login,
                "password": $scope.password,
                "uuid": ""
            }.success(function (result) {

        }).error(function(data, status){
            console.log(data);
        })

        )}

});



chat.controller('MessagesController', function ($scope, $window, $http) {
    $scope.transfer = {};
    $scope.error = false;

    console.log("All message controller");
    $http
        .get('/api/message/all')
        .then(function (response) {
            $scope.messages = response.data;
        });


    $scope.newMessage = '';
    $scope.addMessages = function(){

        $window.location.reload();
        $http.post('api/message/create',{
            "from": {
                "firstName": $scope.messegeFrom,
                "lastName": "Rejnowski",
                "uuid": "1"
            },
            "message": $scope.newMessage,
            "to": {
                "firstName": $scope.messageTo,
                "lastName": "Rejnowski",
                "uuid": "2"
            }
        }).success(function (result) {

            $scope.messages = result;
            $scope.newMessage = '';
            $scope.messegeFrom = '';
            $scope.messageTo= '';
        }).error(function(data, status){
                console.log(data);
        });




    };
});