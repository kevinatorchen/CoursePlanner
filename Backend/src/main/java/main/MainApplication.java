package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//TO RUN THIS DEMO:

/*
    1. Use POST option in Postman.
    2. In header, add key-value pair of "Content-Type" and "application/json"
    3. Send the following JSON request:

    {
        "id": 5000,
        "content": "Hello",
        "name": {
            "firstName": "Kevin",
            "lastName": "Chen"
        }
    }
 */

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}