"use strict";

angular.module('vacanciesGrid').controller("VacanciesEditController", function ($scope, Vacancy) {
    $scope.selectedVacancy = $scope.selectedVacancy || {};

    $scope.close = function () {
        $scope.ngDialog.close();
    };

    $scope.submit = function () {
        Vacancy.update({id: $scope.selectedVacancy.id.toString()}, this.worker).$promise.then(
            function () {
                $location.path('/vacancies');
            }
        );
    };
});
