package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.entities.UserData;
import ar.edu.unlam.tallerweb1.modelo.User;
import ar.edu.unlam.tallerweb1.servicios.auth.IAuthService;
import ar.edu.unlam.tallerweb1.validators.AuthValidator;
import ar.edu.unlam.tallerweb1.exceptions.InvalidFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    private final IAuthService loginService;
    private final AuthValidator validator;

    @Autowired
    public AuthController(IAuthService loginService, AuthValidator validator) {
        this.loginService = loginService;
        this.validator = validator;
    }

    @RequestMapping("/login")
    public ModelAndView getLoginView() {
        ModelMap model = new ModelMap();
        model.put("user", new UserData());

        return new ModelAndView("login", model);
    }

    @RequestMapping("/register")
    public ModelAndView getRegisterView() {
        ModelMap model = new ModelMap();

        model.addAttribute("usuario", new UserData());

        return new ModelAndView("registro-usuario", model);
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ModelAndView registerNewUser(@ModelAttribute("usuario") UserData user) {
        ModelMap model = new ModelMap();
        try {
            validator.validateUserToCreate(user);

            this.loginService.createUser(user);

            return new ModelAndView("redirect:/login");

        } catch (InvalidFieldException exception) {
            model.put("error", exception.getMessage());
            return new ModelAndView("registro-usuario", model);
        }
    }

    @RequestMapping(path = "/validar-login", method = RequestMethod.POST)
    public ModelAndView validarLogin(@ModelAttribute("user") UserData userData) {
        ModelMap model = new ModelMap();

        User userFound = loginService.getUser(userData);

        if (userFound == null) {
            model.put("error", "Usuario o clave incorrecta");
            return new ModelAndView("login", model);
        }

        return new ModelAndView("redirect:/home");
    }


    @RequestMapping(path = "/home", method = RequestMethod.GET)
    public ModelAndView irAHome() {
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/countries");
    }
}
