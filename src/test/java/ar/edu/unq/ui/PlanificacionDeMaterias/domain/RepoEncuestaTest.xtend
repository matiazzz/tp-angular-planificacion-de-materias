package ar.edu.unq.ui.PlanificacionDeMaterias.domain

import org.junit.Test
import org.junit.Assert
import org.junit.Before

class RepoEncuestaTest {
	
	var repoEncuestas = RepoEncuesta.instance
	val encuesta = new Encuesta
	val mail = "mati@mail.com"
	val otroMail = "fulano@mail.com"
	
	@Before
	def setUp(){
		repoEncuestas.addRespuesta("mati@mail.com", encuesta)
	}
	
	@Test
	def void agregarUnaRespuesTest(){
		Assert.assertTrue(repoEncuestas.encuestas.contains(encuesta))
	}
	
	@Test
	def void noPuedeHacerLaEncuestaTest(){
		repoEncuestas.addRespuesta(mail, encuesta)
		Assert.assertFalse(repoEncuestas.puedeHacerLaEncuesta(mail))
	}
	
	@Test
	def void puedeHacerLaEncuestaTest(){
		Assert.assertTrue(repoEncuestas.puedeHacerLaEncuesta(otroMail))
	}
}