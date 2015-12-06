'use strict';

app.service('EncuestaService',function($http){

	return function (errorHandler){
		this.responderEncuesta = function(respuesta, callback){
			$http.get('/responder',respuesta).success(callback).error(errorHandler);
		},

		this.validarMail = function(mail, callback) {
            $http.get('/responder/' + mail).success(callback).error(errorHandler);
        },


		this.getCarreras=function(){
			//obtienelalistadecarrerasconeldetalledelasmateriasylodevuelve
		},

		this.getTurnos=function(){
			//obtienelalistadeturnosylodevuelve
		}
	}
});