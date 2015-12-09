'use strict';
var app = angular.module('encuestaApp');

//VISTA LOGIN CONTROLLER
app.controller('LoginCtrl',function ($scope, $location, $http, $timeout, EncuestaService){
	
	var encuestaService = new EncuestaService();

    $scope.getMail = function(){
        return {"mail" : $scope.mailIngresado};
    }

    $scope.autenticar = function(){
        encuestaService.validarMail($scope.getMail(), function(data) {
            $scope.irALaEncuesta(JSON.parse(data));
        }); 
    }

    $scope.irALaEncuesta = function(pudeHacerLaEncuesta){
        if(pudeHacerLaEncuesta){
            $location.path('/responder/' + $scope.mailIngresado);
        }
        else{
            $scope.mostrarAlerta('Al parecer ya has realizado la encuesta.')
        } 
    }

    $scope.deshabilitarResponder = function(){
        return $scope.mailIngresado == null;
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
        encuestaService.addEncuesta($scope.getRespuesta(),
            function(data) {
                $location.path('/gracias');
        });
    };

    $scope.getRespuesta = function(){
        return {
                "mail" : $routeParams.mail,
                "idCarrera": $scope.carreraSeleccionada.id,
                "materias": $scope.materias,
                "anioIngreso": $scope.anioIngreso,
                "finalesAprobados": $scope.finalesAprobados,
                "finalesDesaprobados": $scope.finalesDesaprobados,
                "cursadasAprobadas": $scope.cursadasAprobadas
            };
    }

    $scope.turnoAdapter = function(turno) {
        if(turno == "MANIANA") {return "Mañana"}
            else if (turno == "TARDE") {return "Tarde"}
                else return "Noche";
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

// GRACIAS CONTROLLER
app.controller('GraciasCtrl',function ($scope, $location, $timeout){
    $timeout(function(){$location.path('/');}, 4000);
});