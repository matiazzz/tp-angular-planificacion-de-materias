'use strict';
var app = angular.module('encuestaApp', ['ngAnimate', 'ngRoute']);

app.config(function($routeProvider){

	$routeProvider.when('/',{templateUrl:'pages/inicio.html',controller:'LoginCtrl'})
	.when('/responder/:mail',{templateUrl:'pages/encuesta.html',controller : 'ResponderCtrl'})
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

    $scope.validarMail = function(){
        encuestaService.validarMail($scope.mailIngresado, function(data) { $scope.puedeHacerLaEncuesta = data; });
    }


    $scope.autenticar = function(){
    	$scope.validarMail()
    	if($scope.puedeHacerLaEncuesta){
            $scope.cambiarVista();
    	}
    	else{
            $scope.mostrarAlerta('Al parecer ya has realizado la encuesta.')
    	}
    	
    }

    $scope.cambiarVista = function(){
    	$location.path('/responder/' + $scope.mailIngresado);
    }

    $scope.mostrarAlerta = function(mensaje){
    	$scope.notificarMensaje(mensaje);
    }


    // FEEDBACK & ERRORES
    $scope.msgs = [];
    $scope.notificarMensaje = function(mensaje) {
        $scope.msgs.push(mensaje);
        
        $timeout(function(){
            while($scope.msgs.length > 0) $scope.msgs.pop();
        }, 3000);
    };

    $scope.errors = [];
    $scope.notificarError = function(mensaje) {
        $scope.errors.push(mensaje);
        $timeout(function(){
            while($scope.errors.length > 0) $scope.errors.pop();
        }, 3000);
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
