'use strict';

angular.module('CouponMaster.login', [])

.controller('LoginCtrl', ['$scope', 'user', function ($scope, user) {
    // Alert array
    $scope.alerts = [], $scope.submitting = false;

    // Close alert
    $scope.closeAlert = function (index) {
        $scope.alerts.splice(index, 1);
    };

    // Handle submit action
    this.login = function () {

        // Prevent submit multiple time
        if ($scope.submitting)
            return;

        if ($scope.email === undefined || $scope.password === undefined) {

            $scope.alerts = [{
                    type: 'danger',
                    msg: "Invalid ID or password"
                }];

            return;
        }

        $scope.submitting = true;

        // Do login
        user.login({
            
            email: $scope.email,
            password: $scope.password,
            keepMeLogin: $scope.keepMeLogin

        }, function (err) {

            // Handle error
            $scope.submitting = false;

            $scope.alerts = [{
                    type: 'danger',
                    msg: "Invalid ID or password"
                }];

        });

    };

}]);