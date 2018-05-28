package main;

import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import model.Greeting;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";

    @RequestMapping(value = "/greeting", method = RequestMethod.POST)
    public String greeting(@RequestBody Greeting input) {
        return input.getName();
    }
}
