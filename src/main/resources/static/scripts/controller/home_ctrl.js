'use strict';

angular.module('CouponMaster.home', ['bw.paging'])

.controller('HomeCtrl', ['$scope', 'util', function ($scope, util) {
        
    $scope.pageSize = 100;
    $scope.dataLoading = true;
    // Data model binding
    $scope.loadData = function (pNumber, pSize) {
        util.callRequest('/allDeals', "GET", {pageNumber: pNumber, pageSize: pSize}).then(function (data) {
            console.log(data);
        	$scope.deals = data;
            $scope.totalPage = data.total_pages;
            $scope.total = data.total_pages;
            $scope.pageSize = data.total_elements;
            $scope.currentPage= data.number;
            $scope.dataLoading = false;
        });
    };

    $scope.loadPage = function (page) {
        $scope.loadData(page - 1, $scope.pageSize);
    };


    $scope.loadData(0, $scope.pageSize);
    
    // angular event listener for event of search box directive
    $scope.$on( 'searchResult', function( event, data ) {
        console.debug( data );
        // binding search data into product list
        $scope.products = response.data;
        $scope.totalPage = data.total_records;
    });
    
    $scope.$on("priceRangeResult", function (event, data) {
        // user finished sliding a handle
        console.debug( data );
    });
    
}]);

