package cz.cvut.fel.stankmic.wa2.frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class MainController extends BaseController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView homepage (HttpSession session) {
        return getModelAndView("home", session, "Home");
    }

}
