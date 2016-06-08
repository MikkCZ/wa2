package cz.cvut.fel.stankmic.wa2.frontend.controller;

import com.rabbitmq.client.ConnectionFactory;
import cz.cvut.fel.stankmic.wa2.data.entities.AsyncResult;
import cz.cvut.fel.stankmic.wa2.data.service.AsyncResultService;
import cz.cvut.fel.stankmic.wa2.rabbit.MyMessage;
import cz.cvut.fel.stankmic.wa2.rabbit.RabbitSubmitter;
import cz.cvut.fel.stankmic.wa2.rabbit.config.RabbitConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping(value = "/async")
public class AsyncController extends BaseController {

    @Autowired
    private AsyncResultService asyncResultService;

    @Autowired
    private ConnectionFactory connectionFactory;

    @RequestMapping(value = "{resultId}", method = RequestMethod.GET)
    public ModelAndView displayResult(
            HttpSession session,
            @PathVariable("resultId") int resultId
    ) throws FileNotFoundException {
        AsyncResult asyncResult = asyncResultService.findById(resultId);
        if(asyncResult == null) {
            throw new FileNotFoundException();
        }
        return getModelAndView("async/view", session, "Unicorn")
                .addObject("result", asyncResult.getResult());
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public ModelAndView newActorForm(HttpSession session) {
        return getModelAndView("async/new", session, "New unicorn?");
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public ModelAndView startAsync() throws IOException {
        int id = asyncResultService.create(null).getId();
        try(RabbitSubmitter rabbitSubmitter = new RabbitSubmitter(RabbitConfig.QUEUE_NAME, connectionFactory.newConnection())) {
            rabbitSubmitter.submit(new MyMessage(id, UUID.randomUUID().toString()).getBytes());
        }
        return new ModelAndView("redirect:/async/"+id);
    }

}
