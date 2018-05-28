package controller;

import model._Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
public class _GreetingController {

    private static final String template = "Hello, %s!";

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greeting(@RequestBody _Greeting input) {
        return input.getNames();
    }
}
