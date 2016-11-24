"use strict";

unityApp.factory('VacancyDelete', ['$resource', function ($resource) {
    return $resource(serverUrl + 'vacancies/delete', null, {
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