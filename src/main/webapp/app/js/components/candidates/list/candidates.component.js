"use strict";

function CandidatesController($scope, Candidate, CandidateDelete, $route, $routeParams) {
    $scope.candidates = $scope.candidates || {};

    $scope.init = function () {
        $scope.candidates = Candidate.query({vacancyId: $routeParams.vacancyId, candidateId: $routeParams.candidateId});
    };

    $scope.verifyAllSelected = function () {
        return this.candidates.filter(function (item) {
                return item.delete === true;
            }).length === this.candidates.length;
    };

    $scope.getImageUrl = function (id) {
        return serverUrl + 'images/' + id;
    };

    $scope.deleteCandidate = function (id) {
        Candidate.delete({vacancyId: $routeParams.vacancyId, candidateId: id}).$promise.then(
            function () {
                $route.reload();
            }
        )
    };


}

angular.module('candidatesGrid').component('candidatesGrid', {
    templateUrl: "app/js/components/candidates/list/candidates.component.html",
    controller: CandidatesController
});

