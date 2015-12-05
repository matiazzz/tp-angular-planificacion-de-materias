package ar.edu.unq.ui.PlanificacionDeMaterias.controller;

import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Encuesta;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Materia;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Respuesta {
  private String mail;
  
  private int carreraId;
  
  private List<Materia> materias;
  
  public Encuesta generarEncuesta() {
    return new Encuesta();
  }
  
  @Pure
  public String getMail() {
    return this.mail;
  }
  
  public void setMail(final String mail) {
    this.mail = mail;
  }
  
  @Pure
  public int getCarreraId() {
    return this.carreraId;
  }
  
  public void setCarreraId(final int carreraId) {
    this.carreraId = carreraId;
  }
  
  @Pure
  public List<Materia> getMaterias() {
    return this.materias;
  }
  
  public void setMaterias(final List<Materia> materias) {
    this.materias = materias;
  }
}
