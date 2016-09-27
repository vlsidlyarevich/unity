"use strict";

unityApp.config(['$locationProvider', '$routeProvider',
    function config($locationProvider, $routeProvider) {

        $routeProvider.when('/workers', {
            template: '<workers></workers>'
        }).when('/workers/:workerId', {
            template: '<worker-profile></worker-profile>'
        }).when('/profiles', {
            template: '<profiles></profiles>'
        }).when('/vacancies', {
            template: '<vacancies></vacancies>'
        }).when('/candidates', {
            template: '<candidates></candidates>'
        }).when('/about', {
            templateUrl: 'js/components/about/about.component.html'
        }).otherwise('/workers');
    }
]);