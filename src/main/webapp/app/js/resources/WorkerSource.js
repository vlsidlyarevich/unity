"use strict";

unityApp.factory('Worker', ['$resource', function ($resource) {
    return $resource(serverUrl + 'workers/:id', null, {
        id: '@id',
        format: 'json',
        update: {
            method: 'PUT'
        }
    })
}]);