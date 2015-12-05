package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Encuesta {
	var List<Materia> materias
	var String carreraSeleccionada
	var int anioIngreso
	var int finalesAprobados
	var int finalesDesaprobados
	var int cursadasAprobadas
	
	new() {
		
	}
	
}