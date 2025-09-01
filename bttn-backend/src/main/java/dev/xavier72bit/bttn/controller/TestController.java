package dev.xavier72bit.bttn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello, This is BlockTraveller Testing Nexus!";
    }
}
