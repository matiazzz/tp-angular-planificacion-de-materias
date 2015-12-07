package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import java.util.List
import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Encuesta {
	var Carrera carrera
	var List<Materia> materias
	var int anioIngreso
	var int finalesAprobados
	var int finalesDesaprobados
	var int cursadasAprobadas
	
	new() {
		
	}
	
	new(Carrera carrera, List<Materia> materias, int anioIngreso, int finalesAprobados,int finalesDesaprobados, int cursadasAprobadas){
		this.materias = materias
		this.carrera = carrera
		this.anioIngreso = anioIngreso
		this.finalesAprobados = finalesAprobados
		this.finalesDesaprobados = finalesDesaprobados
		this.cursadasAprobadas = cursadasAprobadas
	}
	
}