package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public Result index() {
		return redirect("/assets/index.html");
	}

	public Result options(String path) {
		return ok();
	}

}
