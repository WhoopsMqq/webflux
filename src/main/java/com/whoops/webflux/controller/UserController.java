package com.whoops.webflux.controller;

import com.whoops.webflux.pojo.User;
import com.whoops.webflux.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public Flux<User> list() {
        return userService.list();
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PostMapping("")
    public Flux<User> create(@RequestBody Flux<User> users){
        return userService.createOrUpdate(users);
    }

    @PutMapping("/{id}")
    public Mono<User> update(@PathVariable Long id, @RequestBody User user){
        Objects.requireNonNull(user);
        user.setId(id);
        return userService.createOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public Mono<User> delete(@PathVariable Long id){
        return userService.delete(id);
    }

}
