package co.edu.ufps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import co.edu.ufps.commands.GenericServiceImpl;
import co.edu.ufps.model.User;
import co.edu.ufps.repository.UserRepository;
import co.edu.ufps.services.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Integer> implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public CrudRepository<User, Integer> getDao() {
		return userRepository;
	}


	@Override
	public User select(String email, String clave) {
		
		return null;
	}

}
