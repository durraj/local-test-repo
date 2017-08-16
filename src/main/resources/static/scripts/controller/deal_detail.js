'use strict';

angular.module('CouponMaster.deal.details', ['angular-owl-carousel-2'])

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
    $scope.properties = {
            //items: 2,
            onChange: function () {
                console.dir(arguments);
            }
        };

        $scope.ready = function ($api) {
            owlAPi = $api;
        };

        $timeout(function () {
            console.dir(owlAPi);
            owlAPi.trigger('next.owl.carousel',[2000]);
        }, 2000)
    
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


