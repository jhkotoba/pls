package jkt.pls.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import jkt.pls.model.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends ReactiveCrudRepository<ProjectEntity, String> {

}
