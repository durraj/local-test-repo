'use strict';

angular.module('CouponMaster.deal.details', [])

.controller('DealDetailsCtrl', ['$scope', 'util', '$', '$timeout', '$stateParams', 'ShoppingCart', '$http', function ($scope, util, $, $timeout, $stateParams, cart, $http) {


    $scope.proComment = {};
    // Data model biding
    $scope.loadData = function () {
    	$scope.dataLoading = true;
        util.callRequest('dealDetail/' + $stateParams.dealId, "GET").then(function (data) {
        	$scope.deal = data.result;
        	//$scope.product = data.result;
            $scope.dataLoading = false;
        });
        
        util.callRequest('relatedDeals/' + $stateParams.dealId, "GET").then(function (data) {
            $scope.relatedDeals = data;
        });
    };
    
    /*$scope.insertComment = function () {
        $scope.proComment.productId = $stateParams.productId;
        $scope.proComment.rank = 1;
        
        util.callRequest('reviews/add?token=' + "dfasdfasdf", "POST", $scope.proComment).then(function (data) {
        });
    };*/

    $scope.loadData();
    
   /* $timeout(function () {
        // init silder
        $('#similar-product').carousel({
            interval: 5000
        });
    });*/

    
}]);


