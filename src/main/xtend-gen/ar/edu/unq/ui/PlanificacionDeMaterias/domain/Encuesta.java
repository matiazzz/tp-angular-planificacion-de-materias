package ar.edu.unq.ui.PlanificacionDeMaterias.domain;

import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Materia;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Encuesta {
  private List<Materia> materias;
  
  private String carreraSeleccionada;
  
  private int anioIngreso;
  
  private int finalesAprobados;
  
  private int finalesDesaprobados;
  
  private int cursadasAprobadas;
  
  public Encuesta() {
  }
  
  @Pure
  public List<Materia> getMaterias() {
    return this.materias;
  }
  
  public void setMaterias(final List<Materia> materias) {
    this.materias = materias;
  }
  
  @Pure
  public String getCarreraSeleccionada() {
    return this.carreraSeleccionada;
  }
  
  public void setCarreraSeleccionada(final String carreraSeleccionada) {
    this.carreraSeleccionada = carreraSeleccionada;
  }
  
  @Pure
  public int getAnioIngreso() {
    return this.anioIngreso;
  }
  
  public void setAnioIngreso(final int anioIngreso) {
    this.anioIngreso = anioIngreso;
  }
  
  @Pure
  public int getFinalesAprobados() {
    return this.finalesAprobados;
  }
  
  public void setFinalesAprobados(final int finalesAprobados) {
    this.finalesAprobados = finalesAprobados;
  }
  
  @Pure
  public int getFinalesDesaprobados() {
    return this.finalesDesaprobados;
  }
  
  public void setFinalesDesaprobados(final int finalesDesaprobados) {
    this.finalesDesaprobados = finalesDesaprobados;
  }
  
  @Pure
  public int getCursadasAprobadas() {
    return this.cursadasAprobadas;
  }
  
  public void setCursadasAprobadas(final int cursadasAprobadas) {
    this.cursadasAprobadas = cursadasAprobadas;
  }
}
