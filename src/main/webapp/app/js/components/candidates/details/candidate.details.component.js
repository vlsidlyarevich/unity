"use strict";

function candidateDetailsController($scope, $location, $routeParams, Candidate, Image) {

    $scope.candidate = Candidate.find({vacancyId: $routeParams.vacancyId, candidateId: $routeParams.candidateId});

    $scope.submit = function () {
        Candidate.update({id: $routeParams.candidateId.toString()}, this.candidate).$promise.then(
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

    $scope.getImageUrl = function (id) {
        return serverUrl + 'images/' + id;
    };

    $scope.close = function () {
        $location.path('/vacancies/'.concat($routeParams.vacancyId.toString()).concat('/candidates'));
    }
}

angular.module('candidateDetails').component('candidateDetails', {
    templateUrl: "app/js/components/candidates/details/candidate.details.component.html",
    controller: candidateDetailsController
});
