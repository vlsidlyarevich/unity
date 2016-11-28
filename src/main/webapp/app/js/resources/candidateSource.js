"use strict";

unityApp.factory('Candidate', ['$resource', function ($resource) {
    return $resource(serverUrl + 'vacancies/:vacancyId/candidates/:candidateId', null, {
        vacancyId: '@vacancyId',
        candidateId: '@candidateId',
        format: 'json',
        update: {
            method: 'PUT',
            transformResponse: []
        },
        find: {
            method: 'GET',
            transformResponse: function (data) {
                data = angular.fromJson(data);

                data.createdAt = new Date(data.createdAt);
                if (data.updatedAt != null) {
                    data.updatedAt = new Date(data.updatedAt);
                }
                return data;
            }
        },
        delete: {
            method: 'DELETE',
            transformResponse: []
        }
    })
}]);