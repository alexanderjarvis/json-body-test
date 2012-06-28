package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

@With(Authorised.class)
public class Users extends Controller {
	  
	public static Result index() {
		return ok();
	}
	
	public static Result create() {
		return created();
	}
	
	public static Result update(String id) {
		return ok("updated");
	}
  
}
