package cz.cvut.fel.stankmic.wa2.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView loginForm(HttpSession session) {
        if (isLoggedIn(session)) {
            return getModelAndView("home", session, "Home");
        } else {
            return getModelAndView("login", session, "Login");
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView doLogin (
            HttpSession session,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password
    ) throws LoginException {
        if (!isLoggedIn(session)) {
            session.setAttribute("userId", userService.login(email, password).getId());
        }
        return new ModelAndView("redirect:/user/"+session.getAttribute("userId"));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView doLogout(HttpSession session) {
        logout(session);
        return getModelAndView("login", session, "Login");
    }

}
