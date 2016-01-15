package controllers;

import java.util.List;

import models.Segmento;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import util.HttpException;

import com.fasterxml.jackson.databind.JsonNode;

public class Segmentos extends Controller {

	public Result all() {
		String chaveFiltro = request().getQueryString("chave");
		String chaveValor = request().getQueryString("valor");
		
		List<Segmento> segmentos = null;
		
		if (chaveFiltro == null || chaveFiltro.isEmpty() || chaveValor == null || chaveValor.isEmpty()) {
			segmentos = Segmento.finder.all();
		} else {			
			segmentos = Segmento.finder
					.where()
					.ilike(chaveFiltro, "%" + chaveValor + "%")
					.findList();
		}
		

		return ok(Json.toJson(segmentos));
	}

	@BodyParser.Of(BodyParser.Json.class)
	public Result save() {
		Result status = null;
		
		JsonNode body = request().body().asJson();
		Segmento segmento = Json.fromJson(body, Segmento.class);
		try {			
			if (Segmento.findByName(segmento.getNome()) != null) {
				throw new HttpException("Já existe segmento cadastrado com esse nome.");
			}
			segmento.save();
			status = created();
		} catch (Exception e) {
			if (e instanceof HttpException) {
				status = badRequest(Json.toJson(((HttpException)e).getError()));
			} else {
				HttpException internalException = new HttpException("Erro ao salvar.");
				status = internalServerError(Json.toJson(internalException.getError()));
			}
		}
		
		return status;
	}
	
	public Result find(Long id) {
		return ok(Json.toJson(Segmento.finder.byId(id)));
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result update(Long id) {
		JsonNode body = request().body().asJson();
		Segmento segmento = Json.fromJson(body, Segmento.class);
		segmento.update();
		
		return ok();
	}
	
	public Result delete(Long id) {
		Segmento.finder.byId(id).delete();
		
		return ok();
	}
	
}
