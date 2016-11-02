"use strict";

unityApp.service('ImageService', function () {
    this.deleteImage = function ($scope, $http, id) {

        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };

        $http.delete(serverUrl + 'images/' + id, config)
            .success(function (data, status, headers, config) {
                $scope.PostDataResponse = data;
            })
            .error(function (data, status, header, config) {
                $scope.ResponseDetails = "Data: " + data +
                    "<hr />status: " + status +
                    "<hr />headers: " + header +
                    "<hr />config: " + config;
            });
    };

    this.getImage = function ($http, id, callback) {
        $http.get(serverUrl + 'images/' + id).then(function (response) {
            callback(response);
        });
    }
});