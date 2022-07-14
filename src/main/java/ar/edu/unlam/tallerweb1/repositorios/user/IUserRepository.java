package ar.edu.unlam.tallerweb1.repositorios.user;

import ar.edu.unlam.tallerweb1.entities.UserData;
import ar.edu.unlam.tallerweb1.modelo.User;

public interface IUserRepository {
	
	User find(UserData user);
	User findUserById(Long id);
	void save(User user);
}
