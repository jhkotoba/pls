package jkt.pls.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import jkt.pls.handler.SearchHandler;

@Configuration
public class SearchRouter {
	
	@Bean
	protected RouterFunction<ServerResponse> search(SearchHandler searchHandler){
		
		return RouterFunctions
				.route(RequestPredicates.GET("/list")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), searchHandler::list)
				.andRoute(RequestPredicates.GET("/search")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), searchHandler::search)
				.andRoute(RequestPredicates.POST("/insert")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), searchHandler::insert)
				.andRoute(RequestPredicates.POST("/update")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), searchHandler::update);
	}
}
