"use strict";

unityApp.factory('Worker', ['$resource', function ($resource) {
    return $resource(serverUrl + '/workers/:workerId', {
        workerId: '@workerId',
        format: 'json',
        update: {
            method: 'PUT'
        }
    })
}]);