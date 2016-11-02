"use strict";

function WorkersController($scope, $http, Worker, ImageService) {
    $scope.workers = Worker.query();

    // $scope.getWorkers = function () {
    //     WorkerService.getWorkers($scope, $http, function (response) {
    //         $scope.workers = response.data;
    //     });
    // };
    //
    // $scope.getWorkerImage = function (id) {
    //     ImageService.getImage($http, id, function (response) {
    //         return response.data;
    //     });
    // }
}

angular.module('workersGrid').component('workersGrid', {
    templateUrl: "app/js/components/workers/workers.component.html",
    controller: WorkersController
});

