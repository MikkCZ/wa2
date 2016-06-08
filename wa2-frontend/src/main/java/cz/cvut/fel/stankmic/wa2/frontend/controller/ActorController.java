package cz.cvut.fel.stankmic.wa2.frontend.controller;

import cz.cvut.fel.stankmic.wa2.data.entities.Actor;
import cz.cvut.fel.stankmic.wa2.data.entities.Theatre;
import cz.cvut.fel.stankmic.wa2.data.service.ActorService;
import cz.cvut.fel.stankmic.wa2.data.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/actor")
public class ActorController extends BaseController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private TheatreService theatreService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listActors(HttpSession session) {
        List<Actor> actors = actorService.getAll();
        return getModelAndView("actor/actors-list", session, "Actors")
                .addObject("actors", actors);
    }

    @RequestMapping(value = "{actorId}", method = RequestMethod.GET)
    public ModelAndView displayActor(
            HttpSession session,
            @PathVariable("actorId") int actorId
    ) throws FileNotFoundException {
        Actor actor = actorService.loadWithAll(actorId);
        if(actor == null) {
            throw new FileNotFoundException();
        }
        return getModelAndView("actor/actor-view", session, actor.getFirstName()+" "+actor.getLastName())
                .addObject("actor", actor)
                .addObject("actorRatings", actor.getActorRatings().size())
                .addObject("theatres", actor.getTheatres().size());
    }

    @RequestMapping(value = "{actorId}", method = RequestMethod.POST)
    public ModelAndView updateActor(
            @PathVariable("actorId") int actorId,
            @RequestParam(value = "firstname", required = true) String firstName,
            @RequestParam(value = "lastname", required = true) String lastName
    ) throws FileNotFoundException {
        Actor actor = actorService.findById(actorId);
        if(actor == null) {
            throw new FileNotFoundException();
        }
        actorService.update(actor, firstName, lastName);
        return new ModelAndView("redirect:/actor/"+actor.getId());
    }

    @RequestMapping(value = "theatres/{actorId}", method = RequestMethod.GET)
    public ModelAndView displayTheatres(
            HttpSession session,
            @PathVariable("actorId") int actorId
    ) throws FileNotFoundException {
        Actor actor = actorService.loadWithTheatres(actorId);
        if(actor == null) {
            throw new FileNotFoundException();
        }
        List<Theatre> otherTheatres = theatreService.getAll();
        otherTheatres.removeAll(actor.getTheatres());
        return getModelAndView("actor/theatres", session, actor.getFirstName()+" "+actor.getLastName())
                .addObject("actor", actor)
                .addObject("theatres", actor.getTheatres())
                .addObject("otherTheatres", otherTheatres);
    }

    @RequestMapping(value = "theatres/{actorId}", method = RequestMethod.POST)
    public ModelAndView updateTheatres(
            HttpSession session,
            @PathVariable("actorId") int actorId,
            @RequestParam(value = "addtheatre", required = false) Integer addTheatre,
            @RequestParam(value = "removetheatre", required = false) Integer removeTheatre

    ) throws FileNotFoundException {
        Actor actor = actorService.loadWithTheatres(actorId);
        if(actor == null) {
            throw new FileNotFoundException();
        }
        if(addTheatre != null) {
            actorService.addTheatre(actor, addTheatre);
        }
        if(removeTheatre != null) {
            actorService.removeTheatre(actor, removeTheatre);
        }
        return new ModelAndView("redirect:/actor/theatres/"+actorId);
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public ModelAndView newActorForm(HttpSession session) {
        return getModelAndView("actor/actor-new", session, "New actor");
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ModelAndView createActor(
            @RequestParam(value = "firstname", required = true) String firstName,
            @RequestParam(value = "lastname", required = true) String lastName
    ) throws FileNotFoundException {
        return new ModelAndView("redirect:/actor/"+actorService.create(firstName, lastName).getId());
    }

    @RequestMapping(value = "delete/{actorId}", method = RequestMethod.POST)
    public ModelAndView deleteActor(
            @PathVariable("actorId") int actorId
    ) throws FileNotFoundException {
        actorService.delete(actorId);
        return new ModelAndView("redirect:/actor/list");
    }

}
