"use strict";

function VacanciesController($scope, Vacancy, $route) {
    $scope.vacancies = $scope.vacancies || {};

    $scope.init = function () {
        $scope.vacancies = Vacancy.query();
    };

    // $scope.deleteWorker = function (id) {
    //     Worker.delete({id: id}).$promise.then(
    //         function () {
    //             $route.reload();
    //         }
    //     )
    // };
    //
    // $scope.deleteWorkers = function () {
    //     var checked = this.workers.filter(function (item) {
    //         return item.delete === true;
    //     });
    //
    //     if (checked.length === this.workers.length) {
    //         WorkerDelete.deleteQuery({"id": "all"}).$promise.then(
    //             function () {
    //                 $route.reload();
    //             }
    //         );
    //     }
    //     else {
    //         var ids = {};
    //         for (var i = 0; i < checked.length; i++) {
    //             var id = "id".concat(i);
    //             ids[id] = checked[i].id;
    //         }
    //
    //         WorkerDelete.deleteQuery(ids).$promise.then(
    //             function () {
    //                 $route.reload();
    //             }
    //         );
    //     }
    // };
    //
    // $scope.selectAll = function () {
    //     var value = true;
    //
    //     if (this.workers.filter(function (item) {
    //             return item.delete === true;
    //         }).length === this.workers.length) {
    //
    //         value = false;
    //     }
    //
    //     for (var i = 0; i < this.workers.length; i++) {
    //         this.workers[i].delete = value;
    //     }
    // }
}

angular.module('vacanciesGrid').component('vacanciesGrid', {
    templateUrl: "app/js/components/workers/list/vacancies.component.html",
    controller: VacanciesController
});

