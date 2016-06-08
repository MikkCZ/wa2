package cz.cvut.fel.stankmic.wa2.frontend.controller;

import cz.cvut.fel.stankmic.wa2.data.entities.User;
import cz.cvut.fel.stankmic.wa2.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseController {

    @Autowired
    protected UserService userService;

    protected Map<String, String> getMenu() {
        Map<String, String> menu = new LinkedHashMap<>();
        menu.put("Home", "/");
        menu.put("Users", "/user/list");
        menu.put("Actors", "/actor/list");
        menu.put("Theatres", "/theatre/list");
        menu.put("Find a unicorn", "/async/new");
        return menu;
    }

    protected ModelAndView getModelAndView(String viewName, HttpSession session) {
        return getModelAndView(viewName, session, null);
    }

    protected ModelAndView getModelAndView(String viewName, HttpSession session, String title) {
        ModelAndView mv = new ModelAndView(viewName);
        mv.addObject("menu", getMenu());
        mv.addObject("isLoggedIn", isLoggedIn(session));
        if(title != null) {
            mv.addObject("title", title);
        }
        return mv;
    }

    protected boolean isLoggedIn(HttpSession session) {
        Object userId = session.getAttribute("userId");
        if((userId != null) && (userService.findById((Integer)userId) != null)) {
            return true;
        } else {
            logout(session);
            return false;
        }
    }

    protected User getLoggedIn(HttpSession session) throws LoginException {
        if(isLoggedIn(session)) {
            Object userId = session.getAttribute("userId");
            return userService.findById((Integer) userId);
        } else {
            throw new LoginException("User not logged in.");
        }
    }

    protected void logout(HttpSession session) {
        session.removeAttribute("userId");
    }

    @ExceptionHandler(LoginException.class)
    public ModelAndView notLoggedIn(HttpSession session) {
        return getModelAndView("login", session);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public ModelAndView fileNotFound(HttpSession session) {
        return getModelAndView("404", session);
    }

}
