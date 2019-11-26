var app = angular.module('app');

app.controller('RoleController', ['$scope','RoleService', function($scope, RoleService, $mdDialog) {
    
  $scope.addPage = function() {
    $scope.showAdd = true;
    $scope.showUpdate = false;
    $scope.message = 'addPage';
  }

  $scope.updatePage = function(role) {
    var id = role.id;
    var rolename = role.role;
    $scope.message = 'UpdatePage getRoleId' + id +' ' +rolename;
    $scope.showAdd = false;
    $scope.showUpdate = true;
    $scope.selectedrole = role;
  }

    $scope.getAllRoles = function () {
      RoleService.getAllRoles()
        .then(function success(response){
            $scope.roles = response.data;
            $scope.message='getAllRoles';
            $scope.errorMessage = '';
        },
        function error (response ){
            $scope.message='';
            $scope.errorMessage = 'Error getting roles!';
        });
    }

    $scope.addRole = function() {
      var role = $scope.selectedrole;
      RoleService.addRole(role)
      .then(function success(response) {
        role = response.data;
        $scope.roles.push(role);      
        $scope.message = 'addRole' + role;
      },
      function error(response) {
        $scope.message = "Adding Failed";
      });
    }

    $scope.updateRole = function() {
      var role = $scope.selectedrole;
        RoleService.updateRole(role)
        .then(function success(response) {
          $scope.role = response.data;
          $scope.message = ' Data Updated' + role;
        },
        function error(response) {
          $scope.message = 'Data Update Failed' + role.id + ' ' + role.role;
        });
    }

    $scope.deleteRole = function(role) {
      var index = $scope.roles.indexOf(role);
      var id = role.id;
      RoleService.deleteRole(id)
      .then(function success(response) {
        $scope.message = 'Deleted Role with ID:' + id;
        $scope.selectedrole = '';
        $scope.roles.splice(index,1);
      }, 
      function error(response) {
        $scope.message = 'Delete Failed';
      });
    }

    $scope.returnMessage = function() {
      var roleid = $scope.role.id;
        $scope.message="this is message" + roleid;
    }

}]);

app.service('RoleService', ['$http', function($http) {
 
  this.getAllRoles = function getAllRoles() {
    return $http({
        method: 'GET',
        url: '/api/roles'
    });
  }

  this.getRole = function getRole(id) {
    return $http({
        method: 'GET',
        url: '/api/roles/'+id
    });
  }

  this.updateRole = function updateRole(role) {
    return $http({
      method: 'PUT',
      url: '/api/roles/'+role.id,
      data: {'role' : role.role}
    })
  }

  this.deleteRole = function deleteRole(id) {
    return $http({
      method: 'DELETE',
      url: '/api/roles/'+id
    });
  }

  this.addRole = function addRole(role) {
    return $http({ 
      method: 'POST',
      url: '/api/roles',
      data: {'role': role.role}
    });
  }

}]);
