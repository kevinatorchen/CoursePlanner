package controller;

/**
 * Created by Kevin on 5/28/2018.
 */

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.CommitmentRequest;
import model.Schedule;
import org.springframework.web.bind.annotation.*;
import wrapper_classes.CF.RequestWrapperCF;
import wrapper_classes.CF.WrapperClassConverterCF;

import java.util.List;

@RestController
public class EndpointAPIController {

    @RequestMapping(value = "/plancourses", method = RequestMethod.POST)
    public String planCourses(@RequestBody RequestWrapperCF requests) throws JsonProcessingException {
        System.out.println("Object has been deserialized");
        System.out.println("Converting object...");
        List<CommitmentRequest> commitmentRequests = WrapperClassConverterCF.convertRequest(requests);
        System.out.println("Converted objects");
        CoursePlanner.planCourses(commitmentRequests);
        ObjectMapper mapper = new ObjectMapper();
        String schedulesJSON = mapper.writeValueAsString(CoursePlanner.scheduleList);
        return schedulesJSON;
    }
}
