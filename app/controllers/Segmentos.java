package controllers;

import java.util.List;

import models.Segmento;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

public class Segmentos extends Controller {

	public Result all() {
		List<Segmento> segmentos = Segmento.finder.all();
		
		return ok(Json.toJson(segmentos));
	}
	
}
