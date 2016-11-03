"use strict";

unityApp.factory('Worker', ['$resource', function ($resource) {
    return $resource(serverUrl + 'workers/:id', {
        id: '@id',
        format: 'json',
        update: {
            method: 'PUT'
        }
    })
}]);