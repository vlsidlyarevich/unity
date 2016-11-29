"use strict";

function AddCandidateController($scope, $location, Candidate, Image, $routeParams) {
    $scope.candidate = {};

    $scope.submit = function () {
        console.log("Saving: " + this.candidate);
        Candidate.save({vacancyId: $routeParams.vacancyId}, this.candidate).$promise.then(
            function () {
                $location.path('/vacancies/'.concat($routeParams.vacancyId.toString()).concat('/candidates'));
            }
        );
    };

    $scope.fileUploaded = function ($file, $message) {
        $scope.candidate.imageId = $message;
    };

    $scope.resetImage = function ($flow) {
        console.log($scope.candidate.imageId);
        Image.delete({id: $scope.candidate.imageId}, function () {
            console.log("Reset image with id: ".concat(id));
        });

        $flow.cancel();
    };

    $scope.close = function () {
        $location.path('/vacancies/'.concat($routeParams.vacancyId.toString()).concat('/candidates'));
    }
}

angular.module('addCandidate').component('addCandidate', {
    templateUrl: "app/js/components/candidates/add/add.candidate.component.html",
    controller: AddCandidateController
});
