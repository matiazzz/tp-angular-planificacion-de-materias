'use strict';
var app = angular.module('encuestaApp', ['ngAnimate', 'ngRoute']);


//ROUTE CONGFIG
app.config(function($routeProvider){

	$routeProvider.when('/',{templateUrl:'pages/inicio.html', controller:'LoginCtrl'})
	.when('/responder/:mail',{templateUrl:'pages/encuesta.html', controller : 'ResponderCtrl'})
    .when('/gracias',{templateUrl:'pages/gracias.html', controller : 'GraciasCtrl'})
	.otherwise({redirectTo:'/'});

});


//SERVICE
app.factory('EncuestaService', function($http) {
    return function() {
        this.validarMail = function(mail, callback) {
            $http.get('/validarMail/' + mail).success(callback);
        }
        this.getCarreras = function(callback) {
            $http.get('/carreras').success(callback);
        }
        this.getMaterias = function(idCarrera, callback) {
            $http.get('/materias/' + idCarrera).success(callback);
        }
        this.getTurnos = function(callback) {
            $http.get('/turnos').success(callback);
        }
        this.addEncuesta = function(respuesta, callback) {
            $http.post('/responder', respuesta).success(callback);
        }
    }
});


//VISTA LOGIN CONTROLLER
app.controller('LoginCtrl',function ($scope, $location, $http, $timeout, EncuestaService){
	
	var encuestaService = new EncuestaService();

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

    // FEEDBACK
    $scope.msgs = [];
    $scope.notificarMensaje = function(mensaje) {
        $scope.msgs.push(mensaje);
        
        $timeout(function(){
            while($scope.msgs.length > 0) $scope.msgs.pop();
        }, 3000);
    };
});


//VISTA ENCUESTA CONTROLLER
app.controller('ResponderCtrl',function ($scope, $location, $routeParams, $http, $timeout, EncuestaService){
	
	var encuestaService = new EncuestaService();

    $scope.getCarreras = function(){
        encuestaService.getCarreras(function(data) { $scope.carrerasDisponibles = data; });
    }
    $scope.getTurnos = function(){
        encuestaService.getTurnos(function(data) { $scope.turnosDisponibles = data; });
    }

    $scope.materias = [];

    $scope.getCarreras();
    $scope.getTurnos();
    
    $scope.deshabilitarAgregarMaterias = true;
    $scope.seleccionoMateria = false;
    $scope.seleccionoTurno = false;

    $scope.actualizarMaterias = function(){
        encuestaService.getMaterias($scope.carreraSeleccionada.id, function(data) { $scope.materiasDisponibles = data; });
        $scope.datosPorDefault();
    }

    $scope.seleccionarMateria = function(){
        $scope.seleccionoMateria = true;
        $scope.deshabilitarAgregarMaterias = !($scope.seleccionoMateria && $scope.seleccionoTurno);
    }

    $scope.seleccionarTurno =function(){
        $scope.seleccionoTurno = true;
        $scope.deshabilitarAgregarMaterias = !($scope.seleccionoMateria && $scope.seleccionoTurno);
    }

    $scope.datosPorDefault = function(){
        $scope.deshabilitarAgregarMaterias = true;
        $scope.seleccionoMateria = false;
        $scope.seleccionoTurno = false;
        $scope.materias = [];
    }

    $scope.agregarMateria = function(nombreMateria, turnoS){

        if($scope.yaSeAgregoEsaMateria(nombreMateria)){
            $scope.notificarMensaje("Solo se puede agregar un turno por materia.")
        }
        else{
           $scope.materias.push({nombre: nombreMateria, turno: turnoS});
            $scope.deshabilitarAgregarMaterias = true; 
        }
        
    }

    $scope.eliminarMateria = function(materia){
        for(var i in $scope.materias){
            if($scope.materias[i] == materia){
                $scope.materias.splice(i,1);
                break;
            }
        }
    }
    
    $scope.yaSeAgregoEsaMateria = function(nombreMateria){
        for(var i in $scope.materias){
            if($scope.materias[i].nombre == nombreMateria){
                return true;
            }
        }
        return false;
    }

    $scope.deshabilitarEnviarRespuesta = function(){
        return $scope.carreraSeleccionada == null || 
                $scope.anioIngreso == null ||
                $scope.finalesAprobados == null ||
                $scope.finalesDesaprobados == null ||
                $scope.cursadasAprobadas == null ||
                !$scope.agregoMaterias();
    }

    $scope.agregoMaterias = function(){
        return $scope.materias.length > 0
    }

    $scope.enviarRespuesta = function() {
        encuestaService.addEncuesta(
            {
                "mail" : $routeParams.mail,
                "idCarrera": $scope.carreraSeleccionada.id,
                "materias": $scope.materias,
                "anioIngreso": $scope.anioIngreso,
                "finalesAprobados": $scope.finalesAprobados,
                "finalesDesaprobados": $scope.finalesDesaprobados,
                "cursadasAprobadas": $scope.cursadasAprobadas
            },
            function(data) {
                $location.path('/gracias');
        });
    };

    // FEEDBACK
    $scope.msgs = [];
    $scope.notificarMensaje = function(mensaje) {
        $scope.msgs.push(mensaje);
        
        $timeout(function(){
            while($scope.msgs.length > 0) $scope.msgs.pop();
        }, 3000);
    };

});



//VISTA GRACIAS CONTROLLER
app.controller('GraciasCtrl',function ($scope, $location, $timeout){
    $timeout(function(){$location.path('/');}, 4000);
});