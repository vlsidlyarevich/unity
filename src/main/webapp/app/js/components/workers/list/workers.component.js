"use strict";

function WorkersController($scope, Worker) {
    $scope.workers = Worker.query();

    $scope.getImageUrl = function (id) {
        return serverUrl + 'images/' + id;
    }
}

angular.module('workersGrid').component('workersGrid', {
    templateUrl: "app/js/components/workers/list/workers.component.html",
    controller: WorkersController
});

