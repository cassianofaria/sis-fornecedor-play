package controllers;

import java.util.List;

import models.Fornecedor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

public class Fornecedores extends Controller {

	public Result all() {
		List<Fornecedor> fornecedores = Fornecedor.finder.all();

		return ok(Json.toJson(fornecedores));
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result save() {
		JsonNode body = request().body().asJson();
		Fornecedor fornecedor = Json.fromJson(body, Fornecedor.class);
		fornecedor.save();
		
		return created();
	}
	
	public Result find(Long id) {
		return ok(Json.toJson(Fornecedor.finder.byId(id)));
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result update(Long id) {
		JsonNode body = request().body().asJson();
		Fornecedor fornecedor = Json.fromJson(body, Fornecedor.class);
		fornecedor.update();
		
		return ok();
	}
	
	public Result delete(Long id) {
		Fornecedor.finder.byId(id).delete();
		
		return ok();
	}
}
