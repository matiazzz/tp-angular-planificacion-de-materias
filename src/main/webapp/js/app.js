'use strict';
var app = angular.module('encuestaApp', ['ngRoute']);

app.config(function($routeProvider){

	$routeProvider.when('/',{templateUrl:'pages/inicio.html',controller:'LoginCtrl'})
	.when('/responder/:mail',{templateUrl:'pages/encuesta.html',controller : 'ResponderCtrl'})
	.otherwise({redirectTo:'/'});

});