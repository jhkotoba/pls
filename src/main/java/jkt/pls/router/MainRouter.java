//package jkt.pls.router;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//
//@Configuration
//public class MainRouter {
//
//	@Bean
//	protected RouterFunction<ServerResponse> formRouter(){
//		
//		return RouterFunctions.route(RequestPredicates.GET("/"), request -> ServerResponse.ok().render("main.html"));
//	}
//}
