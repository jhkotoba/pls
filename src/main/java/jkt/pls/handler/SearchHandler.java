package jkt.pls.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import jkt.pls.model.request.InsertRequest;
import jkt.pls.service.SearchService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SearchHandler {
	
	private final SearchService searchService;
	
	public Mono<ServerResponse> search(ServerRequest serverRequest){
		
		return Mono.empty();
		
	}
	
	public Mono<ServerResponse> insert(ServerRequest serverRequest){
		
		return serverRequest.bodyToMono(InsertRequest.class)
			.flatMap(searchService::insert)
			.flatMap(s -> ServerResponse.ok()
		            .contentType(MediaType.APPLICATION_JSON)
		            .bodyValue(s)
			)
			// 오류 예외처리
			.onErrorResume(RuntimeException.class, ex -> {
				ex.printStackTrace();
				return ServerResponse.status(HttpStatus.UNAUTHORIZED)
					.contentType(MediaType.APPLICATION_JSON)
					.bodyValue(Map.of(
							"message", ex.getMessage()
		                ));
			});
		
	}
	
	public Mono<ServerResponse> update(ServerRequest serverRequest){
		
		return Mono.empty();
		
	}
	
}
