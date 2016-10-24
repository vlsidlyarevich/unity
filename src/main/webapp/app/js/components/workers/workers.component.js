"use strict";

function WorkersController($scope, $http, WorkerService) {
    WorkerService.getWorkers($scope, $http);
}

angular.module('workersGrid').component('workersGrid', {
    templateUrl: "app/js/components/workers/workers.component.html",
    controller: WorkersController
});

