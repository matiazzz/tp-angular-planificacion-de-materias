package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import java.util.Collection
import java.util.ArrayList

class RepoEncuesta {
	static final RepoEncuesta instance = new RepoEncuesta()
	
	Collection<Encuesta> allEncuestas = new ArrayList<Encuesta>
	Collection<String> mails = new ArrayList<String>
	
	new() {
		addRespuesta("mati", new Encuesta)
	}
	

	def static RepoEncuesta getInstance() {
		return instance
	}

	def void addRespuesta(String mail, Encuesta encuesta) {
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
		if (getMails.findFirst[m | m == mail] == null) true
		else false
	}
}