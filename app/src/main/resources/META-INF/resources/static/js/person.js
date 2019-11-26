var app = angular.module('app');

app.controller('PersonController', ['$scope', '$filter','PersonService', function($scope, $filter, PersonService) {


	$scope.people = PersonService.people;

  $scope.contactTypes = ["Email", "Mobile Number", "Landline"];

  $scope.showPersonList = function showPersonList() {
    $scope.message = 'showPersonList()';
  }


  $scope.getAllPerson = function getAllPerson() {
    PersonService.getAllPerson().
    then(function success(response) {
      $scope.people = response.data;
    });
  }

  $scope.getPerson = function getPerson(id) {
    PersonService.getPerson(id)
    .then(function success(response) {
      $scope.selectedperson = response.data;
      $scope.message = 'Message:' + $scope.selectedperson;
    });
  }

  $scope.getSelectedPerson = function getSelectedPerson() {
  	PersonService.getSelectedPerson()
  	.then(function success(response) {
  		$scope.selectedperson = response.data;
  		$scope.person = response.data;
        $scope.birthdaystring = $filter('date')($scope.selectedperson.birthday, 'yyyy-MM-dd');
        $scope.datehiredstring = $filter('date')($scope.selectedperson.dateHired, 'yyyy-MM-dd');
  	},
  	function error(response) {
  		$scope.message = 'function failed';
  	});
  }

  $scope.showUpdatePage = function showUpdatePage(id) {
    PersonService.getPerson(id)
    .then(function success(response) {
        $scope.message = 'UpdatePagePerson Message' + id;
        $scope.selectedperson = response.data;
      }, 
      function error(response) {
        $scope.message = 'UpdatePagePerson Failed';
      });
  }

  $scope.addPerson = function addPerson(person) {
    PersonService.addPerson(person);
  }

  $scope.updatePerson = function updatePerson() {
    var person = $scope.selectedperson;
    PersonService.updatePerson(person)
    .then(function success(response) {
      $scope.getAllPerson();
      $scope.personPage();
    });
  }

  $scope.deletePerson = function deletePerson(person) {
    var index = $scope.people.indexOf(person);
    var id = person.id;
    $scope.people.splice(index, 1);
    PersonService.deletePerson(id);
  }

  $scope.personRoles = function personRoles() {
  	// $scope.selectedid = id;
  	PersonService.getPersonRoles()
  	.then(function success(response) {
  		$scope.availableRoles = response.data;
  		$scope.message = "Message personRoles:" + ' ' + $scope.person + $scope.person.id;
  	},
  	function error(response) {
  		$scope.message = "Message personRoles Failed " + $scope.selectedid;
  	})
  }

  $scope.updatePersonRoleAdd = function updatePersonRoleAdd(role) {
    var roleId = role.id;
    var index = $scope.availableRoles.indexOf(role);
    PersonService.updatePersonRoleAdd(roleId)
    .then(function success(response) {
      $scope.availableRoles.splice(index, 1);
      $scope.selectedperson.roles.push(role);
      $scope.message = "updatepersonroleadd:success"; 
    });
  }

  $scope.updatePersonRoleDelete = function updatePersonRoleDelete(role) {
    var roleId = role.id;
    var index = $scope.selectedperson.roles.indexOf(role);
    PersonService.updatePersonRoleDelete(roleId)
    .then(function success(response) {
      $scope.selectedperson.roles.splice(index, 1);
      $scope.availableRoles.push(role);
      $scope.message = "updatepersonroledelete:success"; 
    },
    function error(response) {
      $scope.message = "updatepersonroledelete:failed";
    });
  }

  $scope.personContacts = function personContacts() {
    PersonService.getPersonContacts()
    .then(function success(response) {
      $scope.personContactInfos = response.data; 
    });
  }

  $scope.updatePersonContactAdd = function updatePersonContactAdd(contactInfo) {
    PersonService.updatePersonContactAdd(contactInfo)
    .then(function success(response) {
      var contactwithId = response.data;
      $scope.personContactInfos.push(contactwithId);
    }); 
  }

  $scope.updatePersonContactDelete = function updatePersonContactDelete(index) {
    // var contactIndex = contactInfo.index;
    // var index = $scope.personContactInfos.indexOf(contactInfo);
    PersonService.updatePersonContactDelete(index)
    .then(function success(response) {
      $scope.personContactInfos.splice(index, 1);
    });
  }


}]);



app.service('PersonService', ['$http', function($http) {

  var selectedPerson;
  var personId;


  this.getAllPerson = function getAllPerson() {
    return $http({
        method: 'GET',
        url: '/api/people'
    });
  }

  this.getPerson = function getPerson(id) {
  	selectedPerson =  $http({
        method: 'GET',
        url: '/api/people/'+id
    });
    personId = id;
    return selectedPerson;
  }

  this.getPersonRoles = function getPersonRoles() {
  	return $http({
  		method: 'GET',
  		url: '/api/people/roles/'+personId
  	});
  }


  this.getPersonContacts = function getPersonContacts() {
    return $http({
      method: 'GET',
      url: '/api/people/contacts/'+personId
    });
  }

  this.getSelectedPerson = function getSelectedPerson() {
  	return $http({
  		method: 'GET',
  		url: '/api/people/'+personId
  	});
  	// return selectedPerson;
  }

  this.addPerson = function addPerson(person) {
    return $http({
        method: 'POST',
        url: '/api/people',
        data: {'name' : person.name,
              'address' : person.address,
              'birthday' : person.birthday,
              'gwa' : person.gwa,
              'dateHired' : person.dateHired,
              'isCurEmp' : person.isCurEmp }
    });
  }

  this.updatePerson = function updatePerson(person) {
    return $http({
        method: 'PUT',
        url: '/api/people/'+person.id,
        data: {'name' : person.name,
               'address' : person.address,
               'birthday' : person.birthday,
               'gwa' : person.gwa,
               'dateHired' : person.dateHired,
               'isCurEmp' : person.isCurEmp }
    });
  }

  this.updatePersonRoleAdd = function updatePersonRoleAdd(roleId) {
    return $http({
        method: 'POST',
        url: '/api/people/roles/'+personId+'/'+roleId
    });
  }

  this.updatePersonRoleDelete = function updatePersonRoleDelete(roleId) {
    return $http({
        method: 'DELETE',
        url: '/api/people/roles/'+personId+'/'+roleId
    });
  }

  this.updatePersonContactAdd = function updatePersonContactAdd(contactInfo) {
    return $http({
      method: 'POST',
      url: '/api/people/contacts/' + personId,
      data: {'contactType' : contactInfo.contactType,
              'contactInfo' : contactInfo.contactInfo }        
    });
  }

  this.updatePersonContactDelete = function updatePersonContactDelete(contactIndex) {
    return $http({
      method: 'DELETE',
      url: '/api/people/contacts/'+personId+'/'+contactIndex
    });
  }

  this.deletePerson = function deletePerson(id) {
    return $http({
      method: 'DELETE',
      url: '/api/people/'+id
    });
  }

}])
