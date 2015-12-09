package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import java.util.ArrayList
import java.util.Collection

class RepoEncuesta {
	static final RepoEncuesta instance = new RepoEncuesta()
	
	Collection<Encuesta> allEncuestas = new ArrayList<Encuesta>
	Collection<String> mails = new ArrayList<String>
	
	new() {
		addRespuesta("mati", new Encuesta)
		addRespuesta("mati@mail.com", new Encuesta)
	}
	

	def static RepoEncuesta getInstance() {
		return instance
	}

	def void addRespuesta(String mail, Encuesta encuesta) {
		encuesta.verificarCorrictitud()
			
		allEncuestas.add(encuesta)
		mails.add(mail)
	}
	
	def getEncuestas(){
		allEncuestas
	}
	
	def getMails(){
		mails
	}
	
	def puedeHacerLaEncuesta(String mail){
		getMails.findFirst[m | m == mail] == null
	}
}