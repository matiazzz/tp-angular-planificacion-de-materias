package ar.edu.unq.ui.PlanificacionDeMaterias.controller;

import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Carrera;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class CarreraSimple {
  private final String nombre;
  
  private final int id;
  
  public CarreraSimple(final Carrera carrera) {
    int _id = carrera.getId();
    this.id = _id;
    String _nombre = carrera.getNombre();
    this.nombre = _nombre;
  }
  
  @Pure
  public String getNombre() {
    return this.nombre;
  }
  
  @Pure
  public int getId() {
    return this.id;
  }
}
