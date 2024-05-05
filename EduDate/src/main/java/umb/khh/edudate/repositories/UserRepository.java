package umb.khh.edudate.repositories;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import umb.khh.edudate.entity.User;

public interface UserRepository extends Neo4jRepository<User, Long> {

}
