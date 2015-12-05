package ar.edu.unq.ui.PlanificacionDeMaterias.controller

import org.eclipse.xtend.lib.annotations.Accessors
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Materia
import java.util.List
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Encuesta

@Accessors
class Respuesta {
	String mail
	int carreraId
	List<Materia> materias
	
	def generarEncuesta() {
		//TODO
		new Encuesta
	}
	
}