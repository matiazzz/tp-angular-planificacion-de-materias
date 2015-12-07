package ar.edu.unq.ui.PlanificacionDeMaterias.controller

import org.eclipse.xtend.lib.annotations.Accessors
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Materia
import java.util.List
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Encuesta
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.RepoCarreras

@Accessors
class Respuesta {
	var String mail;
	var List<Materia> materias
	var int idCarrera
	var int anioIngreso
	var int finalesAprobados
	var int finalesDesaprobados
	var int cursadasAprobadas
	
	new(){}
	
	new(List<Materia> materias, int idCarrera, int anioIngreso, int finalesAprobados,int finalesDesaprobados, int cursadasAprobadas){
		this.materias = materias
		this.idCarrera = idCarrera
		this.anioIngreso = anioIngreso
		this.finalesAprobados = finalesAprobados
		this.finalesDesaprobados = finalesDesaprobados
		this.cursadasAprobadas = cursadasAprobadas
	}
	
	def generarEncuesta() {
		new Encuesta(RepoCarreras.getInstance.findCarrera(idCarrera), materias, anioIngreso, finalesAprobados, finalesDesaprobados, cursadasAprobadas)
	}
	
}


/*

{
    "mail" : "matias",
    "idCarrera": 1,
    "materias": [{"nombre" : "Objetos I", "turno" : "Noche"}],
    "anioIngreso": 2010,
    "finalesAprobados": 3,
    "finalesDesaprobados": 1,
    "cursadasAprobadas": 5
  }

 */