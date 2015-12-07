package ar.edu.unq.ui.PlanificacionDeMaterias.controller

import org.uqbar.xtrest.json.JSONUtils
import org.uqbar.xtrest.api.annotation.Controller
import org.uqbar.xtrest.api.annotation.Get
import org.uqbar.xtrest.api.Result
import org.uqbar.xtrest.http.ContentType
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.RepoCarreras
import org.uqbar.xtrest.api.XTRest
import org.uqbar.xtrest.api.annotation.Post
import org.uqbar.xtrest.api.annotation.Body
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Carrera
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Encuesta
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.RepoEncuesta
import org.omg.CORBA.UserException
import java.util.List

@Controller
class ResponderController {

	extension JSONUtils = new JSONUtils

	@Get("/carreras")
	def Result carreras() {
		response.contentType = ContentType.APPLICATION_JSON
		var List<CarreraSimple> carrerasSimples = newArrayList()

		for (carrera : RepoCarreras.getInstance.allCarreras) {
			carrerasSimples.add(new CarreraSimple(carrera))
		}
		ok(carrerasSimples.toJson)
	}

	@Get("/materias/:idCarrera")
	def Result materias() {
		response.contentType = ContentType.APPLICATION_JSON
		val id = Integer.valueOf(idCarrera)
		try {
			ok(RepoCarreras.getInstance.findCarrera(id).planDeEstudio.toJson)
		} catch (UserException e) {
			notFound("No existe la carrera solicitada.")
		}
	}

	@Get("/turnos")
	def Result turnos() {
		response.contentType = ContentType.APPLICATION_JSON
		ok(RepoCarreras.getInstance.allTurnos.toJson)
	}

	@Post('/responder')
	def Result responder(@Body String body) {
		var Respuesta respuesta = body.fromJson(Respuesta)
		
		val Carrera carrera = RepoCarreras.getInstance.findCarrera(respuesta.idCarrera)
		
		if(! respuesta.materias.forall[materia | carrera.tieneEnPlanDeEstudio(materia)])
			throw new ErrorEnLaRespuesta("No puede mezclar materias de distintas carreras")
			
		var Encuesta encuesta = respuesta.generarEncuesta();
		
		RepoEncuesta.instance.addRespuesta(respuesta.mail, encuesta)
		
		ok();
	}
	
	@Get("/validarMail/:mail")
	def Result puedeResponderEncuesta() {
		val m = String.valueOf(mail)
		response.contentType = ContentType.APPLICATION_JSON
		ok(RepoEncuesta.instance.puedeHacerLaEncuesta(m).toJson)
	}
	
	@Get("/encuestas")
	def Result getEncuestas() {
		response.contentType = ContentType.APPLICATION_JSON
		ok(RepoEncuesta.instance.encuestas.toJson)
	}

	def static void main(String[] args) {
		XTRest.start(ResponderController, 9001)
	}
}
