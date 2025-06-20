package jkt.pls.service;
// Updated indentation to use tabs

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import jkt.pls.model.entity.ProjectEntity;
import jkt.pls.model.request.ProjectApplyRequest;
import jkt.pls.model.request.ProjectRequest;
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
	
	public Mono<Void> apply(ProjectApplyRequest request){
		
		List<ProjectRequest> list = request.getList();
		List<ProjectEntity> insertList = new ArrayList<>();
		List<ProjectEntity> updateList = new ArrayList<>();
		List<String> deleteIdList = new ArrayList<>();
		
		for(ProjectRequest req : list) {
			
			switch(req.get_status()) {
			case INSERT: insertList.add(ProjectEntity.builder()
					.newFlag(true)
					.projectId(UUID.randomUUID().toString())
					.projectName(req.getProjectName())
					.description(req.getDescription())
					.build()
				);
			break;
			case UPDATE: updateList.add(ProjectEntity.builder()
					.projectId(req.getProjectId())					
					.projectName(req.getProjectName())
					.description(req.getDescription())
					.build()
				);
			break;
			case DELETE: deleteIdList.add(req.getProjectId());
			break;
			case SELECT: /* none */ break;
			}
		}
		
		return Mono.when(
			Flux.fromIterable(insertList).flatMap(entity -> projectRepository.save(entity)).then(),                
			Flux.fromIterable(updateList).flatMap(entity -> projectRepository.save(entity)).then(),
			Flux.fromIterable(deleteIdList).flatMap(id -> projectRepository.deleteById(id)).then()
		);
	}

//    public Mono<Void> delete(String projectId){
//            return projectRepository.deleteById(projectId);
//    }
}
