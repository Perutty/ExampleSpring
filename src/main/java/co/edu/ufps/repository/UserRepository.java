package co.edu.ufps.repository;

import org.springframework.data.repository.CrudRepository;

import co.edu.ufps.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {	
	
}
