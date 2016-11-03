"use strict";

function WorkersController($scope, $http, WorkerService, ImageService) {
    $scope.workers = $scope.workers || {};

    $scope.getWorkers = function () {
        WorkerService.getWorkers($scope, $http, function (response) {
            $scope.workers = response.data;
        });
    };

    $scope.getWorkerImage = function (id) {
        return ImageService.getImage($http, id, function (response) {
            return response.body;
        });
    }
}

angular.module('workersGrid').component('workersGrid', {
    templateUrl: "app/js/components/workers/workers.component.html",
    controller: WorkersController
});

