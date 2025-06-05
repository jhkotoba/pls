package jkt.pls.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import jkt.pls.handler.ProjectHandler;

@Configuration
public class ProjectRouter {
	
	@Bean
	protected RouterFunction<ServerResponse> project(ProjectHandler handler){
		
		return RouterFunctions
            .route(RequestPredicates.GET("/project/find")
            	.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::findAll)
            .andRoute(RequestPredicates.POST("/project/apply")
            	.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::apply)
            .andRoute(RequestPredicates.POST("/project/apply-find")
            	.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), handler::applyAfterFindAll);
            

	}
}
