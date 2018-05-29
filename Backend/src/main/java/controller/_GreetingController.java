package controller;

import model._Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
public class _GreetingController {

    //I found this to be a good guide: http://www.springboottutorial.com/creating-rest-service-with-spring-boot

    //TO RUN THIS DEMO:

    /*
        1. Use POST option in Postman.
        2. In header, add key-value pair of "Content-Type" and "application/json"
        3. Send the following JSON request:

        {
            "id": 5000,
            "content": "Hello",
            "names": [
                {
                    "firstName": "Jane",
                    "lastName": "Doe"
                },
                {
                    "firstName": "James",
                    "lastName": "Smith"
                }
            ]
        }
     */

    private static final String template = "Hello, %s!";

    /*
    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greeting(@RequestBody _Greeting input) {
        return input.getNames();
    }
    */
}
