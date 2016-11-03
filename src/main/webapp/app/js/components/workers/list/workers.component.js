"use strict";

function WorkersController($scope, $http, Worker, ImageService) {
    $scope.workers = Worker.query();

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

