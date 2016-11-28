"use strict";

function CandidatesController($scope, Candidate, CandidateDelete, $route) {
    $scope.candidates = $scope.candidates || {};

    $scope.init = function () {
        $scope.candidates = Candidate.query();
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
        Worker.delete({id: id}).$promise.then(
            function () {
                $route.reload();
            }
        )
    };

    $scope.deleteCandidates = function () {
        var checked = this.candidates.filter(function (item) {
            return item.delete === true;
        });

        if (checked.length === this.candidates.length) {
            CandidateDelete.deleteQuery({"id": "all"}).$promise.then(
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

            CandidateDelete.deleteQuery(ids).$promise.then(
                function () {
                    $route.reload();
                }
            );
        }
    };

    $scope.selectAll = function () {
        var value = true;

        if (this.candidates.filter(function (item) {
                return item.delete === true;
            }).length === this.candidates.length) {

            value = false;
        }

        for (var i = 0; i < this.candidates.length; i++) {
            this.candidates[i].delete = value;
        }
    }
}

angular.module('candidatesGrid').component('candidatesGrid', {
    templateUrl: "app/js/components/candidates/list/candidates.component.html",
    controller: CandidatesController
});

