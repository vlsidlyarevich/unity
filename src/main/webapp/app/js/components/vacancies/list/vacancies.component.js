"use strict";

function VacanciesController($scope, Vacancy, $route, ngDialog) {
    $scope.vacancies = $scope.vacancies || {};

    $scope.editVacancy = function ($index) {
        ngDialog.open({
            template:'app/js/components/vacancies/edit/vacancies.edit.modal.html'
        });
    };

    $scope.init = function () {
        $scope.vacancies = Vacancy.query();
    };
}

angular.module('vacanciesGrid').component('vacanciesGrid', {
    templateUrl: "app/js/components/vacancies/list/vacancies.component.html",
    controller: VacanciesController
});

