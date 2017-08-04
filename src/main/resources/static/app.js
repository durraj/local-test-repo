(function () {
    var springBootAws = angular.module('SpringBootAwsDemo', 
    			[
    				//'ui.bootstrap',
    				'ngRoute',
    			'ngCookies',
    			'SpringBootAwsDemo.directive',
    			'SpringBootAwsDemo.login',
    			//'ui.router',
    			/*'jm.i18next',*/
    			'SpringBootAwsDemo.authen','angularUtils.directives.dirPagination']);

    springBootAws.directive('active', function ($location) {
        return {
            link: function (scope, element) {
                function makeActiveIfMatchesCurrentPath() {
                    if ($location.path().indexOf(element.find('a').attr('href').substr(1)) > -1) {
                        element.addClass('active');
                    } else {
                        element.removeClass('active');
                    }
                }

                scope.$on('$routeChangeSuccess', function () {
                    makeActiveIfMatchesCurrentPath();
                });
            }
        };
    });
    
    springBootAws.directive('fileModel', [ '$parse', function($parse) {
    	return {
    		restrict : 'A',
    		link : function(scope, element, attrs) {
    			var model = $parse(attrs.fileModel);
    			var modelSetter = model.assign;

    			element.bind('change', function() {
    				scope.$apply(function() {
    					modelSetter(scope, element[0].files[0]);
    				});
    			});
    		}
    	};
    } ]);
    
    springBootAws.controller('CreateCustomerCtrl', function ($scope, $location, $http) {
        var self = this;
        
        self.add = function () {            
        	var customerModel = self.model;        	
        	var savedCustomer;
        	
        	var formData = new FormData();
        	formData.append('firstName', customerModel.firstName);
        	formData.append('lastName', customerModel.lastName);
        	formData.append('dateOfBirth', customerModel.dateOfBirth.getFullYear()  + '-' +  (customerModel.dateOfBirth.getMonth() + 1)  + '-' + customerModel.dateOfBirth.getDay());
        	formData.append('image', customerModel.image);
        	formData.append('street', customerModel.address.street);
        	formData.append('town', customerModel.address.town);
        	formData.append('county', customerModel.address.county);
        	formData.append('postcode', customerModel.address.postcode);
        		
        	$scope.saving=true;
        	$http.post('/spring-boot-aws/customers', formData, {	
        	    transformRequest : angular.identity,
    			headers : {
    				'Content-Type' : undefined
    			}
    		}).success(function(data) {
    			$scope.saving=false;
    			console.log("successjson",data);
    			alert("succes",data);
    			/*$scope.saving=false;
    			$location.path("/view-customer/" + savedCustomer.id); */   			
    		}).error(function(data) {
    			$scope.saving=false; 
    		});
        };
    });
    
    springBootAws.controller('RegisterCtrl', function ($scope, $location, $http) {
        var self = this;
        
        self.register = function () {            
        	var customerModel = self.model;        	
        	var savedCustomer;
        	if (customerModel.password === undefined || customerModel.email === undefined) {

                $scope.alerts = [{
                        type: 'danger',
                        msg: "Invalid ID or password"
                    }];

                return;
            }
        	var formData = new FormData();
        	formData.append('firstName', customerModel.firstName);
        	formData.append('lastName', customerModel.lastName);
        	formData.append('email', customerModel.email);
        	formData.append('password', customerModel.password);
        	formData.append('confirmpassword', customerModel.confirmpassword);
        	//formData.append('dateOfBirth', customerModel.dateOfBirth.getFullYear()  + '-' +  (customerModel.dateOfBirth.getMonth() + 1)  + '-' + customerModel.dateOfBirth.getDay());
        	//formData.append('image', customerModel.image);
        	//formData.append('street', customerModel.address.street);
        	//formData.append('town', customerModel.address.town);
        	//formData.append('county', customerModel.address.county);
        	//formData.append('postcode', customerModel.address.postcode);
        		
        	$scope.saving=true;
        	
        	$http.post('/spring-boot-aws/registration', formData, {	
        	    transformRequest : angular.identity,
    			headers : {
    				'Content-Type' : undefined
    			}
    		}).success(function(savedCustomer) {
    			$scope.saving=false;
    			$location.path("/");    			
    		}).error(function(data) {
    			$scope.saving=false; 
    		});
        };
    });
    
   /* springBootAws.controller('LoginCtrl', function ($scope, $location, $http) {
        var self = this;
        $scope.dataLoading=false
        $http.get('/spring-boot-aws/login/').then(function onSuccess(response) {
        	if(response.values.loggedIn)
        		{
        			$scope.dataLoading=true;
        			$scope.loginLoading=false;
        		}else
        			{
	        			$scope.dataLoading=false;
	        			$scope.loginLoading=true;
        			}
        }, function onError(response) {
        	scope.dataLoading=false;
			$scope.loginLoading=true;
        });
        self.login = function () {            
        	var loginModel = self.model;        	
        	var savedCustomer;
        	console.log("scope saving: "+$scope.saving);
        	
        	var formData = new FormData();
        	formData.append('email', loginModel.email);
        	formData.append('password', loginModel.password);
        	formData.append('keepMeLogin', loginModel.keepMeLogin);
        		
        	$scope.saving=true;
        	$http.post('/spring-boot-aws/login', formData, {	
        	    transformRequest : angular.identity,
    			headers : {
    				'Content-Type' : undefined
    			}
    		}).success(function(response) {
    			$scope.saving=false;
    			console.log(" response "+response+" logged in flag : "+response.values.loggedIn)
    			$scope.authenticated= response.values.loggedIn;
    			//$scope.saving=response.loggedIn;
    			//$location.path("/");    			
    		}).error(function(data) {
    			$scope.saving=false; 
    		});
        };
    });*/
    
    springBootAws.controller('ViewCustomerCtrl', function ($scope, $http, $routeParams) {
        
    	var customerId = $routeParams.customerId;    	        
    	$scope.currentPage = 1;
    	$scope.pageSize = 10;
    	
    	$scope.dataLoading = true;
        $http.get('/spring-boot-aws/customers/' + customerId).then(function onSuccess(response) {
        	$scope.customer = response.data;
        	$scope.dataLoading = false;
        }, function onError(response) {
        	$scope.customer = response.statusText;
        	$scope.dataLoading = false;
        });
    });
    
    springBootAws.controller('ViewAllCustomersCtrl', function ($scope, $http) {
    	
    	var self = this;
    	$scope.customers = []; 
    	$scope.searchText;
        
        $scope.dataLoading = true;
        $http.get('/spring-boot-aws/customers').then(function mySucces(response) {
        	$scope.customers = response.data;
        	$scope.dataLoading = false;
        }, function myError(response) {
        	$scope.customer = response.statusText;
        	$scope.dataLoading = false;
        });        
        
        self.delete = function (customerId) {
        	$scope.selectedCustomer = customerId;
        	$scope.customerDelete = true;
        	$http.delete('/spring-boot-aws/customers/' + customerId).then(function onSucces(response) {
            	$scope.customers = _.without($scope.customers, _.findWhere($scope.customers, {id: customerId}));
            	$scope.customerDelete = false;
            }, function onError(){
            	
            });
        },
        
        $scope.searchFilter = function (obj) {
            var re = new RegExp($scope.searchText, 'i');
            return !$scope.searchText || re.test(obj.firstName) || re.test(obj.lastName.toString());
        };
    });
    
    springBootAws.filter('formatDate', function() {
    	return function(input) {
    		return moment(input).format("DD-MM-YYYY");
    	};
    });
    springBootAws.config(['$httpProvider', function( $httpProvider ) {

        $httpProvider.interceptors.push( function ( $q, $injector, $location, $timeout ) {
                
            var api = $injector.get( 'api' ), 
                app = $injector.get( 'app' ), 
                error = $injector.get( 'error' ),
                cookie = $injector.get( '$cookieStore' );
                
                // Init fix token
                
                
            // Lodash
            var _ = $injector.get( '_' );

            // Noty toast
            var noty = $injector.get( 'noty' );
            
            // Manage just one instance of noty
            var notyInstance;
                
            function tokenExpiredHandler() {
               
                // Transition to login page
                $timeout(function() {
                    
                    // Clear token
                    cookie.remove( app.COOKIE_NAME );
                    // Redirect
                    // $location.path( '/login' );
                });
            }   

            return {

              request: function (config) {
                  
                  // Loop to find 
                  angular.forEach( api, function ( a ) {
                      
                     if ( a.token && config.url.indexOf( a.name ) > 0 ) {
                         
                         // Add token to request
                         config.data.token = cookie.get( app.COOKIE_NAME );
                     }
                 });
        
                return config;
              },

              response: function (response) {
                  
                    var contentType = response.headers()['content-type'];
                  
                    contentType = ( contentType ) ? contentType.toLowerCase() : null;
                  
                    if ( contentType === "application/json;charset=utf-8" ) {

                        var status = response.data;

                        // Get error code
                        if ( 'errCode' in status ) {

                            var errCode = status.errCode;

                            if ( errCode > 0 ) {

                                // Check authen
                                if ( _.find( error.AUTH, { code: errCode } ) !== undefined ) {
                                    
                                    // Handler
                                    tokenExpiredHandler();
                                    
                                    return;
                                }
                                
                                // Handle other errors
                                // Show error as toast
                                var e = _.find( error.OTHERS, { code: errCode } );
                                
                                if ( e !== undefined ) {
                                   
                                    var notyOpts = {

                                            text: "There's an erorr occur",
                                            type: 'error', // success, information ...
                                            theme: 'bootstrapTheme',
                                            layout: 'top',
                                            closeWith: ['button', 'click']
                                            // timeout: 5000 // 5s
                                        };
                                   
                                   var opts = {
                                       
                                       text: e.desc
                                   };
                                   
                                   // Check instance
                                   if ( notyInstance ) {
                                       
                                       // Close & clean up
                                       notyInstance.closeCleanUp();
                                   }
                                   
                                   notyInstance = noty( angular.extend( notyOpts, opts ));
                               }
                            }
                        }
                    }

                    return response || $q.when(response);
              },

              responseError: function(rejection) {
                  
                  // Handle reponse error
                  return $q.reject(rejection);
              }
          };
        });

    }])
    
    springBootAws.config(function ($routeProvider) {
        //$routeProvider.when('/home', {templateUrl: 'pages/home.tpl.html'});
    	$routeProvider.when('/home', {templateUrl: 'pages/list-details.tpl.html'});
        $routeProvider.when('/create-customer', {templateUrl: 'pages/createCustomer.tpl.html'});
        $routeProvider.when('/view-customer/:customerId', {templateUrl: 'pages/viewCustomer.tpl.html'});
        $routeProvider.when('/view-all-customers', {templateUrl: 'pages/viewAllCustomers.tpl.html'});
        $routeProvider.when('/register', {templateUrl: 'pages/register.tpl.html'});
        $routeProvider.when('/sign-in', {templateUrl: 'pages/sign-in.tpl.html'});
        $routeProvider.otherwise({redirectTo: '/home'});
    });
    
}());
/*angular.module('jm.i18next').config(['$i18nextProvider', function ( $i18nextProvider ) {
	
	// Configure multi language
	$i18nextProvider.options = {
			lng: 'en',
			useCookie: false,
			useLocalStorage: false,
			debug: true,
			// fallbackLng: 'dev',
			resGetPath: 'i18n/en.json',
			defaultLoadingValue: 'Loading' // ng-i18next option, *NOT* directly supported by i18next
	};
}]);*/