package ar.edu.unlam.tallerweb1.servicios.auth;

import ar.edu.unlam.tallerweb1.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.repositorios.user.IUserRepository;
import ar.edu.unlam.tallerweb1.modelo.User;

// Implelemtacion del Servicio de usuarios, la anotacion @Service indica a Spring que esta clase es un componente que debe
// ser manejado por el framework, debe indicarse en applicationContext que busque en el paquete ar.edu.unlam.tallerweb1.servicios
// para encontrar esta clase.
// La anotacion @Transactional indica que se debe iniciar una transaccion de base de datos ante la invocacion de cada metodo del servicio,
// dicha transaccion esta asociada al transaction manager definido en el archivo spring-servlet.xml y el mismo asociado al session factory definido
// en hibernateCOntext.xml. De esta manera todos los metodos de cualquier dao invocados dentro de un servicio se ejecutan en la misma transaccion
@Service("servicioLogin")
@Transactional
public class AuthService implements IAuthService {

	private final IUserRepository userDao;

	@Autowired
	public AuthService(IUserRepository userDao){
		this.userDao = userDao;
	}

	@Override
	public User getUser(UserData user) {
		return userDao.find(user);
	}

	@Override
	public void createUser(UserData user) {
		User newUser = new User();

		newUser.setPassword(user.getPassword());
		newUser.setEmail(user.getEmail());

		this.userDao.save(newUser);
	}

}
