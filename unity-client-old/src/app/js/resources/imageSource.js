"use strict";

unityApp.factory('Image', ['$resource', function ($resource) {
    return $resource(serverUrl + 'images/:id', {
        id: '@id',
        format: 'file',
        update: {
            method: 'PUT'
        },
        save: {
            method: 'POST',
            transformRequest: formDataObject,
            headers: {'Content-Type': undefined, enctype: 'multipart/form-data'}
        }
    })
}]);

function formDataObject(data) {
    var fd = new FormData();
    angular.forEach(data, function (value, key) {
        fd.append(key, value);
    });
    return fd;
};