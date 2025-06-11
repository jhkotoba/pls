package jkt.pls.repository;
// Updated indentation to use tabs

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import jkt.pls.model.entity.SearchEntity;

@Repository
public interface SearchRepository extends ReactiveCrudRepository<SearchEntity, String> {

}
