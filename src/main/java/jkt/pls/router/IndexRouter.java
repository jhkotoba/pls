package jkt.pls.router;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class IndexRouter {

	@Bean
	protected RouterFunction<ServerResponse> form(){
		return RouterFunctions.route(RequestPredicates.GET("/"), request -> ServerResponse.ok()
				.render("index.html", Map.of("page", "search")));
	}
}
