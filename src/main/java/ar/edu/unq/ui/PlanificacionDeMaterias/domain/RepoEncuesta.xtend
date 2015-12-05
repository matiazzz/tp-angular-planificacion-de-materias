package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import java.util.Collection
import java.util.ArrayList

class RepoEncuesta {
	static final RepoEncuesta instance = new RepoEncuesta()
	
	Collection<Encuesta> allEncuestas = new ArrayList<Encuesta>
	
	new() {
		
	}
	
	def static RepoEncuesta getInstance() {
		return instance
	}

	def void addRespuesta(String mail, Encuesta encuesta) {
		allEncuestas.add(encuesta)
	}
}