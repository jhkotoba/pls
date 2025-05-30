package jkt.pls.service;

import org.springframework.stereotype.Service;

import jkt.pls.model.entity.ProjectEntity;
import jkt.pls.model.request.ProjectApplyReqeust;
import jkt.pls.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProjectService {
	
	private final ProjectRepository projectRepository;	
	
	public Flux<ProjectEntity> findAll(){
		return projectRepository.findAll();
	}
	
	public Mono<ProjectEntity> apply(ProjectApplyReqeust request){
		
		return projectRepository.save(ProjectEntity.builder()
			.projectId(request.getProjectId())
			.projectName(request.getProjectName())
			.description(request.getDescription())
			.build());
	}
}
