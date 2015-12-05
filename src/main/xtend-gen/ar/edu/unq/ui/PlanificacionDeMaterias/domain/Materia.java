package ar.edu.unq.ui.PlanificacionDeMaterias.domain;

import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Turno;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Materia {
  private Turno turno;
  
  private String nombre;
  
  public Materia(final String nombre, final Turno turno) {
    this.turno = turno;
    this.nombre = nombre;
  }
  
  @Pure
  public Turno getTurno() {
    return this.turno;
  }
  
  public void setTurno(final Turno turno) {
    this.turno = turno;
  }
  
  @Pure
  public String getNombre() {
    return this.nombre;
  }
  
  public void setNombre(final String nombre) {
    this.nombre = nombre;
  }
}
