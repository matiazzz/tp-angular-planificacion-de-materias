package ar.edu.unq.ui.PlanificacionDeMaterias.controller

import org.eclipse.xtend.lib.annotations.Accessors

@Accessors
class Mail {
	var String mail
	
	new(){
	}
	new(String mail){
		this.mail = mail
	}
}