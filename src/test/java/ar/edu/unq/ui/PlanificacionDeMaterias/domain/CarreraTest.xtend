package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import org.junit.Test
import org.junit.Assert

class CarreraTest {
	val carrera = new Carrera(1, "Ing. en algo", newArrayList(new Materia("Materia1", Turno.NOCHE)))

	@Test
	def void tieneMateriaEnPlanDeEstudio(){
		Assert.assertTrue(carrera.tieneEnPlanDeEstudio(new Materia("Materia1", Turno.NOCHE)))
	}
		
	@Test
	def void noTieneMateriaEnPlanDeEstudio(){
		Assert.assertFalse(carrera.tieneEnPlanDeEstudio(new Materia("OtraMateria", Turno.NOCHE)))
	}	
}