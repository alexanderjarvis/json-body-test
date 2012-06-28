package controllers;

import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

public class Authorised extends Action.Simple {
	
	@Override
	public Result call(Http.Context ctx) throws Throwable {
		
		if (ctx.request().queryString().containsKey("token")) {
			
			return delegate.call(ctx);

		}
		return unauthorized();
	}
	
}