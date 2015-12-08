package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import org.eclipse.xtend.lib.annotations.Accessors
import java.util.Collection
import java.util.ArrayList
import java.util.List

@Accessors
class RepoCarreras {
	static final RepoCarreras instance = new RepoCarreras()
	
	Collection<Carrera> allCarreras = new ArrayList<Carrera>
	int id = 0

	new() {
		addCarrera("Tecnicatura en Programación Informática", newArrayList(new Materia("Introducción a la programación", Turno.NOCHE), new Materia("Bases de datos", Turno.TARDE), new Materia("Objetos I", Turno.TARDE), new Materia("Objetos II", Turno.TARDE), new Materia("Matematica I", Turno.TARDE)))
		addCarrera("Licenciatura en Desarrollo de Software", newArrayList(new Materia("Matematica III", Turno.TARDE), new Materia("Seguridad Informatica", Turno.TARDE), new Materia("Objetos III", Turno.TARDE), new Materia("Analisis matematico", Turno.TARDE)))
	}

	def static RepoCarreras getInstance() {
		return instance
	}

	def void addCarrera(String nombre, List<Materia> materias) {
		allCarreras.add(new Carrera(id, nombre, materias))
		id = id + 1
	}

	def List<Carrera> buscar(String nombre) {
		allCarreras.filter [ nombre.contains(nombre) ].toList
	}

	def Carrera getCarrera(String nombre) {
		val carrera = allCarreras.findFirst [ nombre == nombre ]
		if (carrera != null) {
			throw new RuntimeException("No existe la carrera "+ nombre)
		}
		carrera
	}
	
	def findCarrera(int idCarrera){
		val Carrera carrera = allCarreras.findFirst[c | c.id == idCarrera]
		if(carrera == null) throw new RuntimeException("No existe la carrera con id "+ id)
		carrera
	}
	
	def List<Turno> allTurnos(){
		newArrayList(Turno.MANIANA, Turno.TARDE, Turno.NOCHE);
	}
}