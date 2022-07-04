package co.edu.ufps.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.ufps.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {	


}
