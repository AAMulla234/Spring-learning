package com.example.learning.springbootaspect;

import com.example.learning.springbootaspect.aspects.CheckSomething;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    @CheckSomething(roles = {"role3"})
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/throwException")
    @CheckSomething(roles = {"role1", "role2"})
    public String throwAnException() throws Exception {
        throw new Exception("Hello from exception!");
    }
}