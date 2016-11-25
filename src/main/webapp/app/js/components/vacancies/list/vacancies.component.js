"use strict";

function VacanciesController($scope, Vacancy, VacancyDelete, $route, ngDialog) {
    $scope.vacancies = $scope.vacancies || {};

    $scope.init = function () {
        $scope.vacancies = Vacancy.query();
    };

    $scope.editVacancy = function ($index) {
        $scope.selectedVacancy = this.vacancies[$index];
        $scope.ngDialog = ngDialog;
        ngDialog.open({
            template: 'app/js/components/vacancies/edit/vacancies.edit.modal.html',
            controller: 'VacanciesEditController',
            scope:$scope
        });
    };

    $scope.deleteVacancy = function (id) {
        Vacancy.delete({id: id}).$promise.then(
            function () {
                $route.reload();
            }
        )
    };

    $scope.deleteVacancies = function () {
        var checked = this.vacancies.filter(function (item) {
            return item.delete === true;
        });

        if (checked.length === this.vacancies.length) {
            VacancyDelete.deleteQuery({"id": "all"}).$promise.then(
                function () {
                    $route.reload();
                }
            );
        }
        else {
            var ids = {};
            for (var i = 0; i < checked.length; i++) {
                var id = "id".concat(i);
                ids[id] = checked[i].id;
            }

            VacancyDelete.deleteQuery(ids).$promise.then(
                function () {
                    $route.reload();
                }
            );
        }
    };

    $scope.selectAll = function () {
        var value = true;

        if (this.vacancies.filter(function (item) {
                return item.delete === true;
            }).length === this.vacancies.length) {

            value = false;
        }

        for (var i = 0; i < this.vacancies.length; i++) {
            this.vacancies[i].delete = value;
        }
    };
}

angular.module('vacanciesGrid').component('vacanciesGrid', {
    templateUrl: "app/js/components/vacancies/list/vacancies.component.html",
    controller: VacanciesController
});

