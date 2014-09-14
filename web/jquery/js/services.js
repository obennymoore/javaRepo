

var deptServices = angular.module("myapp.services", ['ngResource']);

deptServices.factory('Departments', function($resource) {
    return $resource('http://localhost:8081/DeTechDLI/rest/dept/all', {}, {
        query: {method: 'GET', isArray: true}
    });
});