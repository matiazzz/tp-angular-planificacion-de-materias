package ar.edu.unq.ui.PlanificacionDeMaterias.domain;

import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Encuesta;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings("all")
public class RepoEncuesta {
  private final static RepoEncuesta instance = new RepoEncuesta();
  
  private Collection<Encuesta> allEncuestas = new ArrayList<Encuesta>();
  
  public RepoEncuesta() {
  }
  
  public static RepoEncuesta getInstance() {
    return RepoEncuesta.instance;
  }
  
  public void addRespuesta(final String mail, final Encuesta encuesta) {
    this.allEncuestas.add(encuesta);
  }
}
