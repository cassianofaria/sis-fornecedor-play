package controllers;

import java.util.List;

import models.Fornecedor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import util.HttpException;

import com.fasterxml.jackson.databind.JsonNode;

//@With(SecurityAction.class)
public class Fornecedores extends Controller {
	
	public Result all() {
		String chaveFiltro = request().getQueryString("chave");
		String chaveValor = request().getQueryString("valor");
		
		List<Fornecedor> fornecedores = null;
		
		if (chaveFiltro.equals("") || chaveValor.equals("")) {
			fornecedores = Fornecedor.finder.all();
		} else {			
			fornecedores = Fornecedor.finder
					.where()
					.ilike(chaveFiltro, "%" + chaveValor + "%")
					.findList();
		}
		

		return ok(Json.toJson(fornecedores));
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public Result save() {
		Result status = null;
		
		JsonNode body = request().body().asJson();
		Fornecedor fornecedor = Json.fromJson(body, Fornecedor.class);
		try {			
			if (Fornecedor.findByName(fornecedor.getNome()) != null) {
				throw new HttpException("Já existe fornecedor cadastrado com esse nome.");
			}
			fornecedor.save();
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
