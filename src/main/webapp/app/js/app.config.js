"use strict";

unityApp.config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {

        $routeProvider.when('/workers', {
            template: '<workers-grid></workers-grid>'
        }).when('/workers/view/:workerId', {
            template: '<worker-details></worker-details>'
        }).when('/new/worker', {
            template: '<add-worker></add-worker>'
        }).when('/home', {
            template: '<home-page></home-page>'
        }).when('/vacancies', {
            template: '<vacancies-grid></vacancies-grid>'
        }).when('/vacancies/:vacancyId/candidates', {
            template: '<candidates-grid></candidates-grid>'
        }).when('/vacancies/:vacancyId/candidates/view/:candidateId', {
            template: '<candidate-details></candidate-details>'
        }).when('/vacancies/:vacancyId/candidates/new', {
            template: '<add-candidate></add-candidate>'
        }).when('/about', {
            template: '<about-component></about-component>'
        }).otherwise('/workers');
    }
]);