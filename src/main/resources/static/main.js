var chatApp = angular.module('chat', ['ngRoute', 'smart-table']);

chatApp.config(function ($routeProvider) {
    $routeProvider
        .when('/messages',
            {
                controller: 'MessageController',
                templateUrl: 'messages.html'
            })
        .otherwise({redirectTo: '/'});
});

chatApp.service('messageService', function () {
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

chatApp.factory("Service", function ($http) {
    return {
        getAll: $http.get('api/message/page'),
        getPaginated: $http.get('api/message/page',{params:{
            page:1,
            size:10
        }},function (response) {
            console.log(response);
        },function (err) {
            console.log(err)
        })
    };
});

chatApp.controller('MessageController', function ($scope, Service) {
    Service.getAll().then(function (response) {
        console.log(response);
    }, function (error) {
        console.log(error);
    });

    $scope.itemsByPage = 10;
    $scope.callServer = function (tableState) {
        $scope.isLoading = true;
        var pagination = tableState.pagination;
        var start = pagination.start || 0;
        var number = pagination.number || $scope.itemsByPage;
        console.log("All message controller");

        Service.get({
                page: 1 + (start / number),
                size: number
            },
            function (pageable) {
                $scope.pageable = pageable;
                $scope.items = pageable.content;
                tableState.pagination.numberOfPages = pageable.totalPages;
                $scope.isLoading = false;
            });
    };

});


chatApp.controller('MessagesController', function ($scope, $window, $http) {
    $scope.transfer = {};
    $scope.error = false;
    console.log("All message controller");
    $http
        .get('/api/message/all')
        .then(function (response) {
            $scope.messages = response.data;
        });
});