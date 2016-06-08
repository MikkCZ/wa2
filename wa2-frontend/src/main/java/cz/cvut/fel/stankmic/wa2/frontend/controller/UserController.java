package cz.cvut.fel.stankmic.wa2.frontend.controller;

import cz.cvut.fel.stankmic.wa2.data.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listUsers(HttpSession session) {
        List<User> users = userService.getAll();
        return getModelAndView("user/users-list", session, "Users")
                .addObject("users", users);
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public ModelAndView displayUser(
            HttpSession session,
            @PathVariable("userId") int userId
    ) throws FileNotFoundException {
        User user = userService.loadWithRatings(userId);
        if(user == null) {
            throw new FileNotFoundException();
        }
        return getModelAndView("user/user-view", session, user.getEmail())
                .addObject("user", user)
                .addObject("actorRatings", user.getActorRatings().size())
                .addObject("playRatings", user.getPlayRatings().size())
                .addObject("theatreRatings", user.getTheatreRatings().size());
    }

    @RequestMapping(value = "{userId}", method = RequestMethod.POST)
    public ModelAndView updateUser(
            @PathVariable("userId") int userId,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = false) String password
    ) throws FileNotFoundException {
        User user = userService.findById(userId);
        if(user == null) {
            throw new FileNotFoundException();
        }
        userService.update(user, email, password);
        return new ModelAndView("redirect:/user/"+user.getId());
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public ModelAndView newUserForm(HttpSession session) {
        return getModelAndView("user/user-new", session, "Create user");
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ModelAndView createUser(
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "password", required = true) String password
    ) throws FileNotFoundException {
        return new ModelAndView("redirect:/user/"+userService.create(email, password).getId());
    }

    @RequestMapping(value = "delete/{userId}", method = RequestMethod.POST)
    public ModelAndView deleteUser(
            @PathVariable("userId") int userId
    ) throws FileNotFoundException {
        userService.delete(userId);
        return new ModelAndView("redirect:/user/list");
    }

}
