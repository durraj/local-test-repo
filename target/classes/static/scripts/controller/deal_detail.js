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
            },
		    loop:true,
		    margin:10,
		    responsiveClass:true,
		    responsive:{
		        0:{
		            items:1,
		            nav:true
		        },
		        600:{
		            items:2,
		            nav:false
		        },
		        1000:{
		            items:4,
		            nav:true,
		            loop:false
		        }
		    }
        };
    $scope.relatedproperties = {
    		onChange: function () {
                console.dir(arguments);
            },
            autoPlay : false,
            items : 1,
            itemsDesktop : [1199,1],
            itemsDesktopSmall : [991,1],
            itemsTablet: [767,2],
            itemsMobile : [480,1],
            slideSpeed : 3000,
            paginationSpeed : 3000,
            rewindSpeed : 3000,
            navigation : true,
            stopOnHover : true,
            pagination : false,
            scrollPerPage:true
    		/*loop:true,
		    margin:10,
		    responsiveClass:true,
		    responsive:{
		        0:{
		            items:1,
		            nav:true
		        },
		        600:{
		            items:1,
		            nav:false
		        },
		        1000:{
		            items:1,
		            nav:true,
		            loop:false
		        }
		    }*/
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


