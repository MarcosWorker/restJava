package webservice;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class MarcosWorkerHttpVerticle extends AbstractVerticle {
	@Override
	public void start(final Future<Void> startFuture) throws Exception {
		final HttpServer server = vertx.createHttpServer();

		// create router
		final Router router = Router.router(vertx);

		router.post("/marcosworker").produces("application/json").consumes("application/json").handler(ctx -> {
			ctx.response().end(new JsonObject().put("marcos", "Eu sou marcos worker post").toString());
		});

		router.get("/marcosworker").handler(ctx -> {
			ctx.response().end("Eu sou marcos worker");
		});

		router.get("/").handler(ctx -> {
			ctx.response().end("Hello World Microservices vertx");
		});

		server.requestHandler(router).listen(9095, handler -> {
			System.out.println("Server started listening http at " + server.actualPort());
			startFuture.complete();
		});
	}
}
