"use strict";

var unityApp = angular.module('unityApp', ['unityFooter']);

unityApp.controller('WorkersListController', function WorkersListController($scope) {
    $scope.workers = [
        {
            firstName: 'Vladislav',
            lastName: 'Sidlyarevich',
            age: '19'
        }, {
            firstName: 'Nikita',
            lastName: 'Krivitski',
            age: '20'
        }, {
            firstName: 'Karina',
            lastName: 'Denisevich',
            age: '19'
        }
    ];
});