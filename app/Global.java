import java.lang.reflect.Method;

import play.GlobalSettings;
import play.mvc.Action;
import play.mvc.Http.Request;
import action.CorsAction;

public class Global extends GlobalSettings {

	@Override
	public Action<?> onRequest(Request arg0, Method arg1) {
		return new CorsAction();
	}
}