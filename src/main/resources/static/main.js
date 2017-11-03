var chat = angular.module('chat', ['ngRoute','angularUtils.directives.dirPagination','ngTable','mgcrea.ngStrap','ngAnimate','ui.bootstrap']);

chat.config(function ($routeProvider) {
    $routeProvider
        .when('/messages',
            {
                controller: 'MessagesController',
                templateUrl: 'messages.html'
            })
        .when('/clients',
            {
            controller: 'ClientController',
            templateUrl: 'allUsers.html'
            })
        .when('/register',
            {
            controller: 'RegisterController',
            templateUrl: 'register.html'
            })
        .when('/login',
            {
                controller: 'LoginController',
                templateUrl: 'login.html'
            })
        .when('/sent',
            {
                controller: 'MessagesFromMeController',
                templateUrl: 'sentMessages.html'
            })
        .when('/received',
            {
                controller: 'MessagesToMeController',
                templateUrl: 'receivedMessages.html'
            })
        .when('/friends',
            {
                controller: 'FriendsController',
                templateUrl: 'friends.html'
            })
        .when('/profil',
            {
                controller: 'ProfilController',
                templateUrl: 'profil.html'
            })

        .otherwise({redirectTo: '/'});

});


chat.controller('ProfilController', function ($scope, $window, $http) {
    $scope.transfer = {};
    $scope.error = false;
    console.log("All message controller");

    $http
        .get('/api/clients/profil')
        .then(function (response) {
            $scope.transfer = response.data;
        });

    var $ctrl = this;
    $ctrl.items = ['item1', 'item2', 'item3'];

    $ctrl.animationsEnabled = true;
    $ctrl.open = function (size, parentSelector) {
        var parentElem = parentSelector ?
            angular.element($document[0].querySelector('.modal-demo ' + parentSelector)) : undefined;
        var modalInstance = $uibModal.open({
            animation: $ctrl.animationsEnabled,
            ariaLabelledBy: 'modal-title',
            ariaDescribedBy: 'modal-body',
            templateUrl: 'city.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: '$ctrl',
            size: size,
            appendTo: parentElem,
            resolve: {
                items: function () {
                    return $ctrl.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $ctrl.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };
});


chat.controller('ModalInstanceCtrl', function ($uibModalInstance, items) {
    var $ctrl = this;
    $ctrl.items = items;
    $ctrl.selected = {
        item: $ctrl.items[0]
    };

    $ctrl.ok = function () {
        $uibModalInstance.close($ctrl.selected.item);
    };

    $ctrl.cancel = function () {
        $uibModalInstance.dismiss('cancel');
    };
});

chat.component('modalComponent', {
    templateUrl: 'city.html',
    bindings: {
        resolve: '<',
        close: '&',
        dismiss: '&'
    },
    controller: function () {
        var $ctrl = this;

        $ctrl.$onInit = function () {
            $ctrl.items = $ctrl.resolve.items;
            $ctrl.selected = {
                item: $ctrl.items[0]
            };
        };

        $ctrl.ok = function () {
            $ctrl.close({$value: $ctrl.selected.item});
        };

        $ctrl.cancel = function () {
            $ctrl.dismiss({$value: 'cancel'});
        };
    }
});



chat.controller('FriendsController', function ($scope, $window, $http,NgTableParams) {
    $scope.transfer = {};
    $scope.error = false;

    var self = this;
    console.log("All message controller");

    $http
        .get('/api/clients/all')
        .then(function (response) {
            $scope.messages = response.data;
            $scope.transfer = response.data;
        });

    var vm = this;

    vm.usersTable = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.transfer.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });

});

chat.controller('RegisterController',function ($scope, $rootScope, $window, $http, $httpParamSerializer,$location) {
    console.log("Register controller");
    $scope.transfer = {};
    $scope.req = {};
    $scope.register = function () {
         var request = {
             method: 'POST',
             url: '/api/clients/register',
             headers: {
                 'Content-Type': 'application/x-www-form-urlencoded'
             },
             data: $httpParamSerializer($scope.req)
         };
        $http(request)
            .then(function successCallback(response){
                $rootScope.user = response.data;
                $location.path('/login');
                },
                function errorCallback(response) {

                    $scope.error = response.message;
                });

        $scope.req = {};
    };

});

chat.controller('LoginController',function ($scope, $window, $http,$location,$httpParamSerializer,$rootScope) {
    $scope.loginFailed = false;
    console.log("Login controller");
    $scope.credentials = {};

    $scope.login = function () {
        var request = {
            method: 'POST',
            url: '/api/login',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: $httpParamSerializer($scope.credentials)
        };
        $http(request)
            .then(function successCallback(response){
                    $rootScope.authenticated = true;
                    $rootScope.user = response.data;
                    $location.path('/messages');
                    $scope.loginFailed = false;
                },
                function errorCallback(response) {
                    $scope.loginFailed = true;
                });

        $scope.credentials = {};
    };

});

chat.controller('MessagesToMeController', function ($scope, $window, $http ,NgTableParams,$log) {
    $scope.transfer = {};
    $scope.error = false;

    var self = this;
    console.log("All message to me");

    $http
        .get('/api/message/allToMe')
        .then(function (response) {
            $scope.messages = response.data;
            $scope.transfer = response.data;
        });

    var vm = this;

    $scope.totalItems =  $scope.transfer.count;
    $scope.currentPage = 1;

    $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
    };

    $scope.pageChanged = function() {
        $log.log('Page changed to: ' + $scope.currentPage);
    };

    $scope.maxSize = 5;
    $scope.bigTotalItems = $scope.transfer.count;
    $scope.bigCurrentPage = 1;

    vm.usersTable = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.transfer.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });

});


chat.controller('MessagesController', function ($scope, $window, $http ,NgTableParams,$q) {
    $scope.transfer = {};
    $scope.error = false;

    var self = this;
    console.log("All message controller");

    $http
        .get('/api/message/all')
        .then(function (response) {
            $scope.messages = response.data;
            $scope.transfer = response.data;
        });

    var vm = this;

    vm.usersTable = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.transfer.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });

     $scope.newMessage = '';
    $scope.addMessages = function() {
        $window.location.reload();
        /* $window.location.reload();

             var request = {
                 method: 'POST',
                 url: 'api/message/create',
                 headers: {
                     'Content-Type': 'application/x-www-form-urlencoded'
                 },
                 data: $httpParamSerializer($scope.transfer)
             };
             $http(request)
                 .then(function successCallback(response) {
                         $rootScope.user = response.data;
                     },
                     function errorCallback(response) {
                         $scope.error = response.message;
                     });

             $scope.transfer = {};
         };*/

        $http.post('api/message/create', {
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
            $scope.messageTo = '';
        }).error(function (data, status) {
            console.log(data);
        });


    };
});
chat.controller('MessagesFromMeController', function ($scope, $window, $http ,NgTableParams,$q,$log) {
    $scope.transfer = [];
    $scope.error = false;

    var self = this;
    console.log("All message from me");

    $http
        .get('/api/message/allFromMe')
        .then(function (response) {
            $scope.messages = response.data;
            $scope.transfer = response.data;
        });

    var vm = this;

    $scope.totalItems = 5;

    $scope.currentPage = 1;

    $scope.setPage = function (pageNo) {
        $scope.currentPage = pageNo;
    };

    $scope.pageChanged = function() {
        $log.log('Page changed to: ' + $scope.currentPage);
    };

    $scope.maxSize = 5;
    $scope.bigTotalItems =175;
    $scope.bigCurrentPage = 1;


    vm.usersTable = new NgTableParams({
        page: 1,
        count: 5
    }, {
        total: $scope.transfer.length,

        getData:function(params) {
            var deferred = $q.defer();
            $scope.data = $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
            deferred.resolve($scope.data);
        }
    });
    /*  $http
          .get('/api/message/all')
          .then(function (response) {
              $scope.messages = response.data;
              $scope.transfer = response.data;
          });

      $scope.tableParams = new NgTableParams(
          { page: 1, count: 5},
          {
              //dataset: $scope.messages,
              total: $scope.transfer.length, // length of data
              getData: function(params) {
                  $scope.data =  $scope.transfer.slice((params.page() - 1) * params.count(), params.page() * params.count());
                  $defer.resolve(response.slice((params.page() - 1) * params.count(), params.page() * params.count()));
              }
          });*/





    $scope.newMessage = '';
    $scope.addMessages = function() {
        $window.location.reload();
        /* $window.location.reload();

             var request = {
                 method: 'POST',
                 url: 'api/message/create',
                 headers: {
                     'Content-Type': 'application/x-www-form-urlencoded'
                 },
                 data: $httpParamSerializer($scope.transfer)
             };
             $http(request)
                 .then(function successCallback(response) {
                         $rootScope.user = response.data;
                     },
                     function errorCallback(response) {
                         $scope.error = response.message;
                     });

             $scope.transfer = {};
         };*/

        $http.post('api/message/create', {
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
            $scope.messageTo = '';
        }).error(function (data, status) {
            console.log(data);
        });


    };
});