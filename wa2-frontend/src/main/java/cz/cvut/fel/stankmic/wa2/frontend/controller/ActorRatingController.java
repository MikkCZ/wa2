package cz.cvut.fel.stankmic.wa2.frontend.controller;

import cz.cvut.fel.stankmic.wa2.data.entities.Actor;
import cz.cvut.fel.stankmic.wa2.data.entities.ActorRating;
import cz.cvut.fel.stankmic.wa2.data.entities.User;
import cz.cvut.fel.stankmic.wa2.data.service.ActorRatingService;
import cz.cvut.fel.stankmic.wa2.data.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@Controller
@RequestMapping(value = "/actor-rating")
public class ActorRatingController extends BaseController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private ActorRatingService actorRatingService;

    @RequestMapping(value = "{ratingId}", method = RequestMethod.GET)
    public ModelAndView displayRating(
            HttpSession session,
            @PathVariable("ratingId") int ratingId
    ) throws FileNotFoundException {
        ActorRating actorRating = actorRatingService.findById(ratingId);
        if(actorRating == null) {
            throw new FileNotFoundException();
        }
        return getModelAndView("actor-rating/view", session, "Actor rating")
                .addObject("rating", actorRating);
    }

    @RequestMapping(value = "{ratingId}", method = RequestMethod.POST)
    public ModelAndView updateRating(
            @PathVariable("ratingId") int ratingId,
            @RequestParam(value = "comment", required = false) String comment
    ) throws FileNotFoundException {
        ActorRating actorRating = actorRatingService.findById(ratingId);
        if(actorRating == null) {
            throw new FileNotFoundException();
        }
        actorRatingService.update(actorRating, comment);
        return new ModelAndView("redirect:/actor-rating/"+ratingId);
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public ModelAndView newRatingForm(HttpSession session) {
        return getModelAndView("actor-rating/new", session, "New actor rating")
                .addObject("actors", actorService.getAll());
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ModelAndView createRating(
            HttpSession session,
            @RequestParam(value = "actorid", required = true) int actorId,
            @RequestParam(value = "comment", required = false) String comment
    ) throws FileNotFoundException, LoginException {
        int userId = getLoggedIn(session).getId();
        return new ModelAndView("redirect:/actor-rating/"+actorRatingService.create(userId, actorId, comment).getId());
    }

    @RequestMapping(value = "actor/{actorId}", method = RequestMethod.GET)
    public ModelAndView listByActor(
            HttpSession session,
            @PathVariable("actorId") int actorId
    ) throws FileNotFoundException {
        Actor actor = actorService.loadWithAll(actorId);
        if(actor == null) {
            throw new FileNotFoundException();
        }
        return getModelAndView("actor-rating/list", session, actor.getFirstName()+" "+actor.getLastName()+" actor ratings")
                .addObject("actorRatings", actor.getActorRatings());
    }

    @RequestMapping(value = "user/{userId}", method = RequestMethod.GET)
    public ModelAndView listByUser(
            HttpSession session,
            @PathVariable("userId") int userId
    ) throws FileNotFoundException {
        User user = userService.loadWithRatings(userId);
        if(user == null) {
            throw new FileNotFoundException();
        }
        return getModelAndView("actor-rating/list", session, user.getEmail()+" actor ratings")
                .addObject("actorRatings", user.getActorRatings());
    }

}
