package ar.edu.unq.ui.PlanificacionDeMaterias.controller;

import ar.edu.unq.ui.PlanificacionDeMaterias.controller.CarreraSimple;
import ar.edu.unq.ui.PlanificacionDeMaterias.controller.ErrorEnLaRespuesta;
import ar.edu.unq.ui.PlanificacionDeMaterias.controller.Respuesta;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Carrera;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Encuesta;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Materia;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.RepoCarreras;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.RepoEncuesta;
import ar.edu.unq.ui.PlanificacionDeMaterias.domain.Turno;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Request;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.omg.CORBA.UserException;
import org.uqbar.xtrest.api.Result;
import org.uqbar.xtrest.api.XTRest;
import org.uqbar.xtrest.api.annotation.Body;
import org.uqbar.xtrest.api.annotation.Controller;
import org.uqbar.xtrest.api.annotation.Get;
import org.uqbar.xtrest.api.annotation.Post;
import org.uqbar.xtrest.http.ContentType;
import org.uqbar.xtrest.json.JSONUtils;
import org.uqbar.xtrest.result.ResultFactory;

@Controller
@SuppressWarnings("all")
public class ResponderController extends ResultFactory {
  @Extension
  private JSONUtils _jSONUtils = new JSONUtils();
  
  @Get("/carreras")
  public Result carreras(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      response.setContentType(ContentType.APPLICATION_JSON);
      List<CarreraSimple> carrerasSimples = CollectionLiterals.<CarreraSimple>newArrayList();
      RepoCarreras _instance = RepoCarreras.getInstance();
      Collection<Carrera> _allCarreras = _instance.getAllCarreras();
      for (final Carrera carrera : _allCarreras) {
        CarreraSimple _carreraSimple = new CarreraSimple(carrera);
        carrerasSimples.add(_carreraSimple);
      }
      String _json = this._jSONUtils.toJson(carrerasSimples);
      _xblockexpression = ResultFactory.ok(_json);
    }
    return _xblockexpression;
  }
  
  @Get("/materias/:idCarrera")
  public Result materias(final String idCarrera, final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      response.setContentType(ContentType.APPLICATION_JSON);
      final Integer id = Integer.valueOf(idCarrera);
      Result _xtrycatchfinallyexpression = null;
      try {
        RepoCarreras _instance = RepoCarreras.getInstance();
        Carrera _findCarrera = _instance.findCarrera((id).intValue());
        List<Materia> _planDeEstudio = _findCarrera.getPlanDeEstudio();
        String _json = this._jSONUtils.toJson(_planDeEstudio);
        _xtrycatchfinallyexpression = ResultFactory.ok(_json);
      } catch (final Throwable _t) {
        if (_t instanceof UserException) {
          final UserException e = (UserException)_t;
          _xtrycatchfinallyexpression = ResultFactory.notFound("No existe la carrera solicitada.");
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      _xblockexpression = _xtrycatchfinallyexpression;
    }
    return _xblockexpression;
  }
  
  @Get("/turnos")
  public Result turnos(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      response.setContentType(ContentType.APPLICATION_JSON);
      RepoCarreras _instance = RepoCarreras.getInstance();
      List<Turno> _allTurnos = _instance.allTurnos();
      String _json = this._jSONUtils.toJson(_allTurnos);
      _xblockexpression = ResultFactory.ok(_json);
    }
    return _xblockexpression;
  }
  
  @Post("/responder")
  public Result responder(@Body final String body, final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) {
    Result _xblockexpression = null;
    {
      Respuesta respuesta = this._jSONUtils.<Respuesta>fromJson(body, Respuesta.class);
      RepoCarreras _instance = RepoCarreras.getInstance();
      int _carreraId = respuesta.getCarreraId();
      final Carrera carrera = _instance.findCarrera(_carreraId);
      List<Materia> _materias = respuesta.getMaterias();
      final Function1<Materia, Boolean> _function = new Function1<Materia, Boolean>() {
        public Boolean apply(final Materia materia) {
          return Boolean.valueOf(carrera.tieneEnPlanDeEstudio(materia));
        }
      };
      boolean _forall = IterableExtensions.<Materia>forall(_materias, _function);
      boolean _not = (!_forall);
      if (_not) {
        throw new ErrorEnLaRespuesta("No puede mezclar materias de distintas carreras");
      }
      Encuesta encuesta = respuesta.generarEncuesta();
      RepoEncuesta _instance_1 = RepoEncuesta.getInstance();
      String _mail = respuesta.getMail();
      _instance_1.addRespuesta(_mail, encuesta);
      _xblockexpression = ResultFactory.ok();
    }
    return _xblockexpression;
  }
  
  public static void main(final String[] args) {
    XTRest.start(ResponderController.class, 9000);
  }
  
  public void handle(final String target, final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
    {
    	Matcher matcher = 
    		Pattern.compile("/carreras").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		
    		
    	    Result result = carreras(target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/materias/(\\w+)").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		String idCarrera = matcher.group(1);
    		
    		
    	    Result result = materias(idCarrera, target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/turnos").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Get") && matcher.matches()) {
    		// take parameters from request
    		
    		// take variables from url
    		
    		
    	    Result result = turnos(target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    {
    	Matcher matcher = 
    		Pattern.compile("/responder").matcher(target);
    	if (request.getMethod().equalsIgnoreCase("Post") && matcher.matches()) {
    		// take parameters from request
    		String body = readBodyAsString(request);
    		
    		// take variables from url
    		
    		
    	    Result result = responder(body, target, baseRequest, request, response);
    	    result.process(response);
    	    
    		response.addHeader("Access-Control-Allow-Origin", "*");
    	    baseRequest.setHandled(true);
    	    return;
    	}
    }
    this.pageNotFound(baseRequest, request, response);
  }
  
  public void pageNotFound(final Request baseRequest, final HttpServletRequest request, final HttpServletResponse response) throws IOException, ServletException {
    response.getWriter().write(
    	"<html><head><title>XtRest - Page Not Found!</title></head>" 
    	+ "<body>"
    	+ "	<h1>Page Not Found !</h1>"
    	+ "	Supported resources:"
    	+ "	<table>"
    	+ "		<thead><tr><th>Verb</th><th>URL</th><th>Parameters</th></tr></thead>"
    	+ "		<tbody>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/carreras</td>"
    	+ "				<td></td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/materias/:idCarrera</td>"
    	+ "				<td>idCarrera</td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>GET</td>"
    	+ "				<td>/turnos</td>"
    	+ "				<td></td>"
    	+ "			</tr>"
    	+ "			<tr>"
    	+ "				<td>POST</td>"
    	+ "				<td>/responder</td>"
    	+ "				<td>body</td>"
    	+ "			</tr>"
    	+ "		</tbody>"
    	+ "	</table>"
    	+ "</body>"
    );
    response.setStatus(404);
    baseRequest.setHandled(true);
  }
}
