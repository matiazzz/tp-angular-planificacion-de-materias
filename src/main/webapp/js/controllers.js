'use strict';
var app = angular.module('encuestaApp', ['ngRoute']);

app.config(function($routeProvider){

	$routeProvider.when('/',{templateUrl:'index.html',controller:'LoginCtrl'})
	.when('/responder/',{templateUrl:'pages/encuesta.html',controller : 'ResponderCtrl'})
	.otherwise({redirectTo:'/'});

});

app.factory('EncuestaService', function($http) {
    return function() {
        this.validarMail = function(mail, callback) {
            $http.get('/validarMail/' + mail).success(callback);
        }
    }
});

app.controller('LoginCtrl',function ($scope, $location, $http, $timeout, EncuestaService){
	
	var encuestaService = new EncuestaService(function(data, status) { 
        if (data.error) {
            $scope.notificarError("Error: " + data.error);
        }
        else {
            $scope.notificarError(status + ": " + data);
        }
    });

    $scope.ejecutar = function(){
    	encuestaService.validarMail($scope.mailIngresado, function(data) { $scope.puedeHacerLaEncuesta = data; });
    	if($scope.puedeHacerLaEncuesta){
    		$scope.cambiarVista
    	}
    	else{
    		$scope.mostrarAlerta
    	}
    	
    }

    $scope.cambiarVista = function(){
    	$location.path('responder/' + $scope.mailIngresado);
    }

    $scope.mostrarAlerta = function(){
    	console.log("NO puede hacer la encuesta");
    }
});


app.controller('ResponderCtrl',function ($scope, $http, $timeout, EncuestaService){
	
	var encuestaService = new EncuestaService(function(data, status) { 
        if (data.error) {
            $scope.notificarError("Error: " + data.error);
        }
        else {
            $scope.notificarError(status + ": " + data);
        }
    });
});
