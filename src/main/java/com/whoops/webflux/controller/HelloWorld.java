package com.whoops.webflux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class HelloWorld {

    @GetMapping("hello")
    @ResponseBody
    public Mono<String> hello(){
        return Mono.just("Hello World!");
    }

}
