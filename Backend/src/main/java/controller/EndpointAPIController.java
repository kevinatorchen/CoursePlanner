package controller;

/**
 * Created by Kevin on 5/28/2018.
 */

import model.CommitmentRequest;
import org.springframework.web.bind.annotation.*;
import wrapper_classes.CF.RequestWrapperCF;
import wrapper_classes.CF.WrapperClassConverterCF;

import java.util.List;

@RestController
public class EndpointAPIController {

    @RequestMapping(value = "/plancourses", method = RequestMethod.POST)
    public RequestWrapperCF planCourses(@RequestBody RequestWrapperCF requests) {
        System.out.println("Object has been deserialized");
        System.out.println("Converting object...");
        List<CommitmentRequest> commitmentRequests = WrapperClassConverterCF.convertRequest(requests);
        System.out.println("Converted objects");
        return requests;
    }
}
