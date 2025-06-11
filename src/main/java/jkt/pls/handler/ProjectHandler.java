package jkt.pls.handler;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import jkt.pls.model.request.ProjectApplyRequest;
import jkt.pls.model.response.ProjectResponse;
import jkt.pls.service.ProjectService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProjectHandler {
	
	private final ProjectService projectService;	
	
	public Mono<ServerResponse> findAll(ServerRequest serverRequest){
		
	return projectService.findAll()
		.collectList()
		.flatMap(list -> ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(list.stream().map(m -> ProjectResponse.builder()
            	.projectId(m.getProjectId())
            	.projectName(m.getProjectName())
            	.description(m.getDescription())
            	.build())
            )
        )
        .onErrorResume(RuntimeException.class, ex ->		        	
            ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(Map.of("message", ex.getMessage()))
        );
	}

    public Mono<ServerResponse> apply(ServerRequest serverRequest){
	
		return serverRequest.bodyToMono(ProjectApplyRequest.class)
			.flatMap(projectService::apply)
			.flatMap(p -> ServerResponse.ok()
		            .contentType(MediaType.APPLICATION_JSON)		            
		            .bodyValue(p)
			)
			.onErrorResume(RuntimeException.class, ex ->		        	
            	ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            	.contentType(MediaType.APPLICATION_JSON)
	                .bodyValue(Map.of("message", ex.getMessage()))
			);
    }
    
    public Mono<ServerResponse> applyAfterFindAll(ServerRequest serverRequest){
		
		return serverRequest.bodyToMono(ProjectApplyRequest.class)
			.flatMap(projectService::apply)
			.then(projectService.findAll().collectList())
			.flatMap(list -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(list.stream().map(m -> ProjectResponse.builder()
                	.projectId(m.getProjectId())
                	.projectName(m.getProjectName())
                	.description(m.getDescription())
                	.build())
                )
	        )				
			.onErrorResume(RuntimeException.class, ex ->
            	ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
	            	.contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(Map.of("message", ex.getMessage()))
                        );
    }

    public Mono<ServerResponse> delete(ServerRequest serverRequest){
                String projectId = serverRequest.pathVariable("projectId");
                return projectService.delete(projectId)
                                .then(ServerResponse.ok()
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .build())
                                .onErrorResume(RuntimeException.class, ex ->
                                                ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                                                .contentType(MediaType.APPLICATION_JSON)
                                                                .bodyValue(Map.of("message", ex.getMessage()))
                                );
    }

}
