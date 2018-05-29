package controller;

/**
 * Created by Kevin on 5/28/2018.
 */

import org.springframework.web.bind.annotation.*;
import wrapper_classes.CF.RequestWrapperCF;

@RestController
public class EndpointAPIController {

    @RequestMapping(value = "/plancourses", method = RequestMethod.POST)
    public RequestWrapperCF planCourses(@RequestBody RequestWrapperCF requests) {
        System.out.println("Object has been deserialized");
        return requests;
    }
}
