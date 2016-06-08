package cz.cvut.fel.stankmic.wa2.frontend.controller;

import cz.cvut.fel.stankmic.wa2.data.entities.Theatre;
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
@RequestMapping(value = "/theatre")
public class TheatreController extends BaseController {

    @Autowired
    private TheatreService theatreService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listTheatres(HttpSession session) {
        List<Theatre> theatres = theatreService.getAll();
        return getModelAndView("theatre/theatres-list", session, "Theatres")
                .addObject("theatres", theatres);
    }

    @RequestMapping(value = "{theatreId}", method = RequestMethod.GET)
    public ModelAndView displayTheatre(
            HttpSession session,
            @PathVariable("theatreId") int theatreId
    ) throws FileNotFoundException {
        Theatre theatre = theatreService.loadWithRatings(theatreId);
        if(theatre == null) {
            throw new FileNotFoundException();
        }
        return getModelAndView("theatre/theatre-view", session, theatre.getName())
                .addObject("theatre", theatre)
                .addObject("theatreRatings", theatre.getTheatreRatings().size());
    }

    @RequestMapping(value = "{theatreId}", method = RequestMethod.POST)
    public ModelAndView updateTheatre(
            @PathVariable("theatreId") int theatreId,
            @RequestParam(value = "name", required = true) String name
    ) throws FileNotFoundException {
        Theatre theatre = theatreService.findById(theatreId);
        if(theatre == null) {
            throw new FileNotFoundException();
        }
        theatreService.update(theatre, name);
        return new ModelAndView("redirect:/theatre/"+theatre.getId());
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public ModelAndView newTheatreForm(HttpSession session) {
        return getModelAndView("theatre/theatre-new", session, "New theatre");
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ModelAndView createTheatre(
            @RequestParam(value = "name", required = true) String name
    ) throws FileNotFoundException {
        return new ModelAndView("redirect:/theatre/"+ theatreService.create(name).getId());
    }

    @RequestMapping(value = "delete/{theatreId}", method = RequestMethod.POST)
    public ModelAndView deleteTheatre(
            @PathVariable("theatreId") int theatreId
    ) throws FileNotFoundException {
        theatreService.delete(theatreId);
        return new ModelAndView("redirect:/theatre/list");
    }

}
