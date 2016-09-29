"use strict";

unityApp.config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {

        $routeProvider.when('/workers', {
            template: '<workers-grid></workers-grid>'
        }).when('/workers/:workerId', {
            template: '<worker-profile></worker-profile>'
        }).when('/new/worker', {
            template: '<add-worker></add-worker>'
        }).when('/profiles', {
            template: '<profiles></profiles>'
        }).when('/vacancies', {
            template: '<vacancies></vacancies>'
        }).when('/candidates', {
            template: '<candidates></candidates>'
        }).when('/about', {
            template: '<about-component></about-component>'
        }).otherwise('/workers');
    }
]);