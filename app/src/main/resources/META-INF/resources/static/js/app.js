var app = angular.module('app',['ngRoute', 'ngMaterial']);

app.config(['$routeProvider', function($routeProvider) {
  $routeProvider
    .when('/home', {
      templateUrl: 'templates/home.html'
    })
    .when('/crud/id', {
      templateUrl: 'templates/userCrud.html'
    })
    .when('/roles', {
      templateUrl: 'templates/roles.html',
      controller: 'RoleController'
    })
    .when('/person', {
      templateUrl: 'templates/personlist.html',
      controller: 'PersonController'
    })
    .when('/person/:personid', {
      templateUrl: 'templates/singleperson.html',
      controller: 'PersonController'
    })
    .when('/person/:personid/update', {
      templateUrl: 'templates/updateperson.html',
      controller: 'PersonController'
    })    
    .when('/person/:personid/update/roles', {
      templateUrl: 'templates/personroles.html',
      controller: 'PersonController'
    })
    .when('/person/:personid/update/contacts', {
      templateUrl: 'templates/personcontacts.html',
      controller: 'PersonController'
    })
    .when('/person/add', {
      templateUrl: 'templates/addperson.html',
      controller: 'PersonController'
    })
    .otherwise({
      redirectTo: '/home'
    })
}]);
