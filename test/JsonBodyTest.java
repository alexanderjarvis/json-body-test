
import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.CREATED;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.UNAUTHORIZED;
import static play.test.Helpers.GET;
import static play.test.Helpers.POST;
import static play.test.Helpers.PUT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.routeAndCall;
import static play.test.Helpers.running;
import static play.test.Helpers.status;

import org.junit.Test;

import play.libs.Json;
import play.mvc.Result;

public class JsonBodyTest {
	
	private static final String QUERY = "?token=randomstring";
	
	@Test
	public void unauthorised() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = routeAndCall(fakeRequest(GET, "/users"));
			    assertThat(status(result)).isEqualTo(UNAUTHORIZED);
			}
		});
	}
	
	@Test
	public void authorised() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				Result result = routeAndCall(fakeRequest(GET, "/users" + QUERY));
			    assertThat(status(result)).isEqualTo(OK);
			}
		});
	}
	
	@Test
	public void createUserRouteAndCall() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				String body = "{\"firstName\":\"John\",\"lastName\":\"Smith\"}";
				
				Result result = routeAndCall(fakeRequest(POST, "/users" + QUERY).withJsonBody(Json.parse(body)));
				
			    assertThat(status(result)).isEqualTo(CREATED);
			}
		});
	}	
	
	@Test
	public void updateUserRouteAndCall() {
		running(fakeApplication(), new Runnable() {
			public void run() {
				String body = "{\"firstName\":\"John\",\"lastName\":\"Smith\"}";
				
				Result result = routeAndCall(fakeRequest(PUT, "/users/" + "123" + QUERY).withJsonBody(Json.parse(body)));
				
			    assertThat(status(result)).isEqualTo(OK);
			}
		});
	}
}
