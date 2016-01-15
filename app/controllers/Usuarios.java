package controllers;

import models.Usuario;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import util.HttpException;

import com.fasterxml.jackson.databind.JsonNode;

public class Usuarios extends Controller {

	@BodyParser.Of(BodyParser.Json.class)
    public Result login(){
        Result result = null;
        
        JsonNode jsonNode = request().body().asJson();
        Usuario login = Json.fromJson(jsonNode, Usuario.class);
        
        try{
            
            Usuario usuario = Usuario.finder.query().where().eq("nome", login.getNome()).findUnique();
            if(usuario == null){
                throw new HttpException("Usuário não encontrado.");
            }
            
            if(!usuario.getSenha().equals(login.getSenha())){
                throw new HttpException("Usuário não encontrado.");
            }
            
            result = ok(Json.toJson(usuario));
            
        } catch(Exception e){
            result = badRequest();
        }
        
        return result;
    }
	
}
