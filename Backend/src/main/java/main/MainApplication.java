package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}