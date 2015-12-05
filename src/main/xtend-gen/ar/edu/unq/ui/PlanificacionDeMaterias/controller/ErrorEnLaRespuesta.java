package ar.edu.unq.ui.PlanificacionDeMaterias.controller;

@SuppressWarnings("all")
public class ErrorEnLaRespuesta extends RuntimeException {
  public ErrorEnLaRespuesta(final String string) {
    super(string);
  }
}
