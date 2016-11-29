"use strict";

unityApp.factory('CandidateDelete', ['$resource', function ($resource) {
    return $resource(serverUrl + 'vacancies/:vacancyId/candidates/delete', {vacancyId: '@vacancyId'}, {
        format: 'json',
        deleteQuery: {
            method: 'POST'
        }
    })
}]);
