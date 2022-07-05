package co.edu.ufps.services;

import co.edu.ufps.commands.GenericService;
import co.edu.ufps.model.User;

public interface UserService extends GenericService<User, Integer>{
	public User select(String email, String clave);
}
