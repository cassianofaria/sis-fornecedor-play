package action;

import models.Usuario;
import play.libs.F;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

public class SecurityAction extends Action.Simple {

	public F.Promise<Result> call(Http.Context ctx) throws Throwable {
		if (getTokenFromHeader(ctx) != null) {
			String[] token = getTokenFromHeader(ctx).split(":");
			Usuario usuario = Usuario.finder.where().eq("nome", token[0]).eq("senha", token[1]).findUnique();
			if (usuario != null) {
				ctx.request().withUsername(usuario.getSenha());
				ctx.args.put("currentUser", usuario);
				return delegate.call(ctx);
			}
		}
		Result unauthorized = Results.unauthorized("unauthorized");
		return F.Promise.pure(unauthorized);
	}

	private String getTokenFromHeader(Http.Context ctx) {
		String[] authTokenHeaderValues = ctx.request().headers().get("Authorization");
		if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
			return authTokenHeaderValues[0];
		}
		return null;
	}

}
