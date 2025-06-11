package jkt.pls.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import jkt.pls.model.request.InsertRequest;
import jkt.pls.model.request.SearchRequest;
import jkt.pls.service.SearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class SearchHandler {
	
	private final SearchService searchService;
	
        public Mono<ServerResponse> list(ServerRequest serverRequest){

                log.info("SearchHandler.list");
                String keyword = serverRequest.queryParam("keyword").orElse("");
		
		return searchService.list(new SearchRequest())
			.collectList()
			.flatMap(list ->
            ServerResponse.ok()
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(list)
	        )
	        .onErrorResume(RuntimeException.class, ex ->		        	
	            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(Map.of("message", ex.getMessage()))
	        );
		
//		return serverRequest.bodyToMono(SearchRequest.class)
//				.map(m -> {
//					System.out.println(m);
//					return m;
//				})
//				.flatMapMany(searchService::list)
//				.collectList()
//				.flatMap(list -> ServerResponse.ok()
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .bodyValue(list)
//	        )
//	        .onErrorResume(RuntimeException.class, ex ->		        	
//	            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .bodyValue(Map.of("message", ex.getMessage()))
//	        );
		//return Mono.empty();
		
	}
	
	public Mono<ServerResponse> search(ServerRequest serverRequest){
		
		return Mono.empty();
		
	}
	
	public Mono<ServerResponse> insert(ServerRequest serverRequest){
		
		return serverRequest.bodyToMono(InsertRequest.class)
			.map(f -> {
				// 테스트 데이터 삽입
				f.setDocumentId(1L);
				return f;
			})
			.flatMap(searchService::insert)
			.flatMap(s -> ServerResponse.ok()
		            .contentType(MediaType.APPLICATION_JSON)
		            .bodyValue(s)
			)
			// 오류 예외처리
                        .onErrorResume(RuntimeException.class, ex -> {
                                log.error("Insert failed", ex);
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
	
	public Mono<ServerResponse> findProject(ServerRequest serverRequest){
		
		return searchService.findProject()
			.collectList()
			.flatMap(list -> ServerResponse.ok()
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(list)
	        )
	        .onErrorResume(RuntimeException.class, ex ->		        	
	            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(Map.of("message", ex.getMessage()))
	        );
	}
	
}
