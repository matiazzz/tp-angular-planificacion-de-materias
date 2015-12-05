package ar.edu.unq.ui.PlanificacionDeMaterias.domain;

import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Materia;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class Carrera {
  private final int id;
  
  private final String nombre;
  
  private List<Materia> planDeEstudio;
  
  public Carrera(final int id, final String nombre, final List<Materia> materias) {
    this.id = id;
    this.nombre = nombre;
    this.planDeEstudio = materias;
  }
  
  public boolean agregarMaterias(final Materia materias) {
    return this.planDeEstudio.add(materias);
  }
  
  public boolean tieneEnPlanDeEstudio(final Materia materia) {
    return this.planDeEstudio.contains(materia);
  }
  
  @Pure
  public int getId() {
    return this.id;
  }
  
  @Pure
  public String getNombre() {
    return this.nombre;
  }
  
  @Pure
  public List<Materia> getPlanDeEstudio() {
    return this.planDeEstudio;
  }
  
  public void setPlanDeEstudio(final List<Materia> planDeEstudio) {
    this.planDeEstudio = planDeEstudio;
  }
}
