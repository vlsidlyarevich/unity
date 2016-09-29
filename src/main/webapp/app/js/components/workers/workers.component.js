"use strict";

function WorkersController($scope, $http) {
    $http.get(serverUrl + 'workers').
    then(function(response) {
        $scope.workers = response.data;
    });
}

angular.module('workersGrid').component('workersGrid', {
    templateUrl: "js/components/workers/workers.component.html",
    controller: WorkersController
});

