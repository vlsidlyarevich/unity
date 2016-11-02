// "use strict";
//
// unityApp.service('ImageService', function () {
//     this.deleteImage = function ($scope, $http, id) {
//
//         var config = {
//             headers: {
//                 'Content-Type': 'application/json'
//             }
//         };
//
//         $http.delete(serverUrl + 'images/' + id, config)
//             .success(function (data, status, headers, config) {
//                 $scope.PostDataResponse = data;
//             })
//             .error(function (data, status, header, config) {
//                 $scope.ResponseDetails = "Data: " + data +
//                     "<hr />status: " + status +
//                     "<hr />headers: " + header +
//                     "<hr />config: " + config;
//             });
//     };
//
//     this.getImage = function ($http, id, callback) {
//         $http.get(serverUrl + 'images/' + id).then(function (response) {
//             callback(response);
//         });
//     }
// });
"use strict";

unityApp.factory('Image', ['$resource', function ($resource) {
    return $resource(serverUrl + '/images/:imageId', {
        imageId: '@imageId',
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