"use strict";

angular.module('vacanciesGrid').controller("VacanciesAddController", function ($scope, $route, Vacancy) {
    $scope.vacancy = {};

    $scope.close = function () {
        $scope.ngDialog.close();
    };

    $scope.submit = function () {
        Vacancy.save(this.vacancy).$promise.then(
            function () {
                $route.reload();
                $scope.ngDialog.close();
            }
        );
    };
});
