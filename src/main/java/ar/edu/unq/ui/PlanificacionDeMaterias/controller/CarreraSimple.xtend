package ar.edu.unq.ui.PlanificacionDeMaterias.controller

import org.eclipse.xtend.lib.annotations.Accessors
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Carrera

@Accessors
class CarreraSimple {
	
	val String nombre
	val int id 
	
	new(Carrera carrera) {
		this.id = carrera.id
		this.nombre = carrera.nombre
	}
	
}