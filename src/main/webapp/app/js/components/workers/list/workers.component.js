"use strict";

function WorkersController($scope, Worker) {
    $scope.workers = $scope.workers || {};

    $scope.init = function () {
        $scope.workers = Worker.query();
    };

    $scope.getImageUrl = function (id) {
        return serverUrl + 'images/' + id;
    };

    $scope.deleteWorkers = function () {
        console.log(this.workers.filter(function (item) {
            return item.delete === true;
        }))
    };

    $scope.selectAll = function () {
        var value = true;

        if (this.workers.filter(function (item) {
                return item.delete === true;
            }).length === this.workers.length) {

            value = false;
        }

        for (var i = 0; i < this.workers.length; i++) {
            this.workers[i].delete = value;
        }
    }
}

angular.module('workersGrid').component('workersGrid', {
    templateUrl: "app/js/components/workers/list/workers.component.html",
    controller: WorkersController
});

