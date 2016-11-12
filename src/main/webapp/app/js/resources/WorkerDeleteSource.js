"use strict";

unityApp.factory('WorkerDelete', ['$resource', function ($resource) {
    return $resource(serverUrl + 'workers/delete', null, {
        format: 'json',
        update: {
            method: 'PUT'
        },
        deleteQuery: {
            method: 'POST'
        }
    })
}])
;