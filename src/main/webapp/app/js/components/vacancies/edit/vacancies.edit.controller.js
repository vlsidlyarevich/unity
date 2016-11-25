"use strict";

angular.module('vacanciesGrid').controller("VacanciesEditController", function ($scope, $route, Vacancy) {
    $scope.vacancy = $scope.selectedVacancy || {};

    $scope.close = function () {
        $scope.ngDialog.close();
    };

    $scope.submit = function () {
        Vacancy.update({id: $scope.selectedVacancy.id.toString()}, this.vacancy).$promise.then(
            function () {
                $route.reload();
                $scope.ngDialog.close();
            }
        );
    };
});
