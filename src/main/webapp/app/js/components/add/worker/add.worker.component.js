"use strict";

function AddWorkerController($scope, $http, $location, WorkerService) {
    $scope.worker = $scope.worker || {};

    $scope.submit = function (worker) {
        console.log(worker);
        WorkerService.saveWorker($scope, $http, worker, function (result) {
            console.log(result);
        });

        $location.path('/workers');
    };

    $scope.fileUploaded = function ($file, $message, $flow) {
        $scope.worker.imageId = $message;
    };

    $scope.close = function () {
        $location.path('/workers')
    }
}

angular.module('addWorker').component('addWorker', {
    templateUrl: "app/js/components/add/worker/add.worker.component.html",
    controller: AddWorkerController
});

