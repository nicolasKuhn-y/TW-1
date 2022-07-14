package ar.edu.unlam.tallerweb1.repositorios.user;

import ar.edu.unlam.tallerweb1.entities.UserData;
import ar.edu.unlam.tallerweb1.modelo.Reserve;
import ar.edu.unlam.tallerweb1.modelo.User;

import java.util.List;

public interface IUserRepository {
	
	User find(UserData user);
	User findUserById(Long id);
	void save(User user);
}
