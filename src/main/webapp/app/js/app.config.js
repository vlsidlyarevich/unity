"use strict";

unityApp.config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {

        $routeProvider.when('/workers', {
            template: '<workers-grid></workers-grid>'
        }).when('/workers/view/:workerId', {
            template: '<worker-details></worker-details>'
        }).when('/new/worker', {
            template: '<add-worker></add-worker>'
        }).when('/profiles', {
            template: '<profiles></profiles>'
        }).when('/vacancies', {
            template: '<vacancies-grid></vacancies-grid>'
        }).when('/candidates', {
            template: '<candidates></candidates>'
        }).when('/about', {
            template: '<about-component></about-component>'
        }).otherwise('/workers');
    }
]);