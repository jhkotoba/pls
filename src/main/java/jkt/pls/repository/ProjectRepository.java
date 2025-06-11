package jkt.pls.repository;
// Updated indentation to use tabs

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import jkt.pls.model.entity.ProjectEntity;

@Repository
public interface ProjectRepository extends ReactiveCrudRepository<ProjectEntity, String> {

}
