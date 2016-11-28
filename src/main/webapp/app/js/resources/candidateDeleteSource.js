"use strict";

unityApp.factory('CandidateDelete', ['$resource', function ($resource) {
    return $resource(serverUrl + 'vacancies/:vacancyId/candidates/delete', null, {
        vacancyId: '@vacancyId',
        format: 'json',
        update: {
            method: 'PUT'
        },
        deleteQuery: {
            method: 'POST'
        }
    })
}]);
