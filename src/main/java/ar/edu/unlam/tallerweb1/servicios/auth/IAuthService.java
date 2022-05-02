package ar.edu.unlam.tallerweb1.servicios.auth;

import ar.edu.unlam.tallerweb1.entities.UserData;
import ar.edu.unlam.tallerweb1.modelo.User;

// Interface que define los metodos del Servicio de Usuarios.
public interface IAuthService {
	User getUser(UserData user);
	void createUser(UserData user);
}
