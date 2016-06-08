package cz.cvut.fel.stankmic.wa2.frontend.controller;

import cz.cvut.fel.stankmic.wa2.data.entities.AsyncResult;
import cz.cvut.fel.stankmic.wa2.data.service.AsyncResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;

@RestController
@RequestMapping(value = "/asyncrest")
public class AsyncAPIController {

    @Autowired
    private AsyncResultService asyncResultService;

    @RequestMapping(value = "{resultId}", method = RequestMethod.GET)
    @ResponseBody
    public AsyncResult resultAPI(
            @PathVariable("resultId") int resultId
    ) throws FileNotFoundException {
        AsyncResult asyncResult = asyncResultService.findById(resultId);
        if(asyncResult == null || asyncResult.getResult() == null) {
            throw new FileNotFoundException();
        }
        return asyncResult;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public void fileNotFound(HttpSession session) {
    }

}
