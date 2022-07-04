package co.edu.ufps.repository;

<<<<<<< HEAD
=======

>>>>>>> branch 'main' of https://github.com/Perutty/ExampleSpring.git
import org.springframework.data.repository.CrudRepository;

import co.edu.ufps.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {	
<<<<<<< HEAD
	
	
=======
	public abstract User findByEmailAndClave(String email, String clave);
>>>>>>> branch 'main' of https://github.com/Perutty/ExampleSpring.git
}
