"use strict";

function HeaderController($scope, $location) {
    $scope.isActive = function (viewLocation) {
        return $location.path().indexOf(viewLocation) == 0;
    };
}

angular.module('unityHeader').component('unityHeader', {
    templateUrl: "js/components/header/header.component.html",
    controller: HeaderController
});

