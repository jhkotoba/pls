package jkt.pls.service;

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
    	
    	for(ProjectRequest req : list) {
    		
    		switch(req.get_status()) {
                case INSERT: insertList.add(ProjectEntity.builder()
                                        .newFlag(true)
                                        .projectId(UUID.randomUUID().toString())
                                        .projectName(req.getProjectName())
                                        .description(req.getDescription())
                                        .useYn("Y")
                                        .build()
                        );
                break;
                case UPDATE: updateList.add(ProjectEntity.builder()
                                        .projectId(req.getProjectId())
                                        .projectName(req.getProjectName())
                                        .description(req.getDescription())
                                        .useYn("Y")
                                        .build()
                        );
                break;
                case SELECT: /* none */ break;
                }
        }

        return Mono.when(
                Flux.fromIterable(insertList).flatMap(entity -> projectRepository.save(entity)).then(),
                Flux.fromIterable(updateList).flatMap(entity -> projectRepository.save(entity)).then()
        );
    }

    public Mono<Void> delete(String projectId){
        return projectRepository.findById(projectId)
                .flatMap(entity -> {
                    entity.setUseYn("N");
                    return projectRepository.save(entity).then();
                });
    }

//    public Mono<Void> delete(String projectId){
//            return projectRepository.deleteById(projectId);
//    }
}
