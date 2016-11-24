"use strict";

unityApp.factory('Vacancy', ['$resource', function ($resource) {
    return $resource(serverUrl + 'vacancies/:id', null, {
        id: '@id',
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
}])
;