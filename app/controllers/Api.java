package controllers;

import java.util.List;

import dto.FornecedorDTO;
import models.Fornecedor;
import play.mvc.Result;
import play.libs.Json;
import play.mvc.Controller;

public class Api extends Controller {

	public Result listFornecedores() {
		List<FornecedorDTO> fornecedores = Fornecedor.findLight();
		
		return ok(Json.toJson(fornecedores));
	}
	
}
