package action;

import play.libs.F.Function0;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http.Context;
import play.mvc.Http.Response;
import play.mvc.Result;

public class CorsAction extends Action.Simple {

	  public Promise<Result> call(Context context) throws Throwable {
	        Response response = context.response();
	        response.setHeader("Access-Control-Allow-Origin", "*");
	    
	        System.out.println("--------------- Requisitado ---------------");
	        
	        // Handle preflight requests
	        if (context.request().method().equals("OPTIONS")) {
	          
	          response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
	          response.setHeader("Access-Control-Max-Age", "3600");
	          response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, X-Auth-Token");
	          response.setHeader("Access-Control-Allow-Credentials", "true");
	    
	          return corsOk();
	        }
	    
	        response.setHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Auth-Token");
	        return delegate.call(context);
	  }

	  private Promise<Result> corsOk() {
	        return Promise.promise(new Function0<Result>() {
	          public Result apply() {
	            return ok();
	          }
	        });
	  }

	}
