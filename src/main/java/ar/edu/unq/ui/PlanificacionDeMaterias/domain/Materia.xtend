package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Materia {
	Turno turno
	String nombre
	
	new(){}
	
	new(String nombre, Turno turno){
		this.turno = turno
		this.nombre = nombre
	}
}

public enum Turno{
	MANIANA,
	TARDE,
	NOCHE
}