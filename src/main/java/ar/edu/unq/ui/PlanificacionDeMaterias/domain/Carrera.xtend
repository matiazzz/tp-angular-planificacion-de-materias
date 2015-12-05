package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import org.eclipse.xtend.lib.annotations.Accessors
import java.util.List

@Accessors
class Carrera {
	
	val int id;
	val String nombre
	var List<Materia> planDeEstudio;
	
	new(int id, String nombre, List<Materia> materias) {
		this.id = id
		this.nombre = nombre
		this.planDeEstudio = materias
	}
	
	def agregarMaterias(Materia materias){
		planDeEstudio.add(materias)
	}
	
	def tieneEnPlanDeEstudio(Materia materia) {
		planDeEstudio.contains(materia)
	}
	
}