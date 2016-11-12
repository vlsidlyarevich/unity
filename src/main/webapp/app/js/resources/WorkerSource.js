"use strict";

unityApp.factory('Worker', ['$resource', function ($resource) {
    return $resource(serverUrl + 'workers/:id', null, {
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

                data.birthday = new Date(data.birthday);
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