package ar.edu.unq.ui.PlanificacionDeMaterias.domain;

import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Carrera;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Materia;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Turno;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

@Accessors
@SuppressWarnings("all")
public class RepoCarreras {
  private final static RepoCarreras instance = new RepoCarreras();
  
  private Collection<Carrera> allCarreras = new ArrayList<Carrera>();
  
  private int id = 0;
  
  public RepoCarreras() {
    Materia _materia = new Materia("Objetos I", Turno.NOCHE);
    Materia _materia_1 = new Materia("Objetos II", Turno.TARDE);
    Materia _materia_2 = new Materia("Matematica I", Turno.TARDE);
    ArrayList<Materia> _newArrayList = CollectionLiterals.<Materia>newArrayList(_materia, _materia_1, _materia_2);
    this.addCarrera("Tecnicatura en Programacion Informatica", _newArrayList);
    Materia _materia_3 = new Materia("Seguridad Informatica", Turno.MANIANA);
    Materia _materia_4 = new Materia("Objetos III", Turno.NOCHE);
    Materia _materia_5 = new Materia("Analisis matematico", Turno.TARDE);
    ArrayList<Materia> _newArrayList_1 = CollectionLiterals.<Materia>newArrayList(_materia_3, _materia_4, _materia_5);
    this.addCarrera("Licenciatura en Desarrollo de Software", _newArrayList_1);
  }
  
  public static RepoCarreras getInstance() {
    return RepoCarreras.instance;
  }
  
  public void addCarrera(final String nombre, final List<Materia> materias) {
    Carrera _carrera = new Carrera(this.id, nombre, materias);
    this.allCarreras.add(_carrera);
    this.id = (this.id + 1);
  }
  
  public List<Carrera> buscar(final String nombre) {
    final Function1<Carrera, Boolean> _function = new Function1<Carrera, Boolean>() {
      public Boolean apply(final Carrera it) {
        return Boolean.valueOf(nombre.contains(nombre));
      }
    };
    Iterable<Carrera> _filter = IterableExtensions.<Carrera>filter(this.allCarreras, _function);
    return IterableExtensions.<Carrera>toList(_filter);
  }
  
  public Carrera getCarrera(final String nombre) {
    Carrera _xblockexpression = null;
    {
      final Function1<Carrera, Boolean> _function = new Function1<Carrera, Boolean>() {
        public Boolean apply(final Carrera it) {
          return Boolean.valueOf(Objects.equal(nombre, nombre));
        }
      };
      final Carrera carrera = IterableExtensions.<Carrera>findFirst(this.allCarreras, _function);
      boolean _notEquals = (!Objects.equal(carrera, null));
      if (_notEquals) {
        throw new RuntimeException(("No existe la carrera " + nombre));
      }
      _xblockexpression = carrera;
    }
    return _xblockexpression;
  }
  
  public Carrera findCarrera(final int idCarrera) {
    Carrera _xblockexpression = null;
    {
      final Function1<Carrera, Boolean> _function = new Function1<Carrera, Boolean>() {
        public Boolean apply(final Carrera c) {
          int _id = c.getId();
          return Boolean.valueOf((_id == idCarrera));
        }
      };
      final Carrera carrera = IterableExtensions.<Carrera>findFirst(this.allCarreras, _function);
      boolean _equals = Objects.equal(carrera, null);
      if (_equals) {
        throw new RuntimeException(("No existe la carrera con id " + Integer.valueOf(this.id)));
      }
      _xblockexpression = carrera;
    }
    return _xblockexpression;
  }
  
  public List<Turno> allTurnos() {
    return CollectionLiterals.<Turno>newArrayList(Turno.MANIANA, Turno.TARDE, Turno.NOCHE);
  }
  
  @Pure
  public Collection<Carrera> getAllCarreras() {
    return this.allCarreras;
  }
  
  public void setAllCarreras(final Collection<Carrera> allCarreras) {
    this.allCarreras = allCarreras;
  }
  
  @Pure
  public int getId() {
    return this.id;
  }
  
  public void setId(final int id) {
    this.id = id;
  }
}
