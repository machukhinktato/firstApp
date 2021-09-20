package it.machukhinktato.spring.repository;

import it.machukhinktato.spring.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
//public interface PersonRepository extends CrudRepository<Person, Long> {
}
