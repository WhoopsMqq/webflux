package com.whoops.webflux.service;

import com.whoops.webflux.pojo.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {

    private final Map<Long, User> userMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init(){
        userMap.put(1L,new User(1L,"whoops","whoops@qq.com"));
        userMap.put(2L,new User(2L,"mqq","mqq@qq.com"));
        userMap.put(3L,new User(3L,"cjj","cjj@qq.com"));
    }

    public Flux<User> list(){
        return Flux.fromIterable(userMap.values());
    }

    public Flux<User> getByIds(Flux<Long> ids) {
        return ids.flatMap(id -> Mono.justOrEmpty(this.userMap.get(id)));
    }

    public Mono<User> getById(Long id) {
        return Mono.justOrEmpty(this.userMap.get(id))
                .switchIfEmpty(Mono.error(new Exception("不存在对应用户!")));
    }

    public Flux<User> createOrUpdate(Flux<User> users) {
        return users.doOnNext(user -> this.userMap.put(user.getId(), user));
    }

    public Mono<User> createOrUpdate(User user) {
        this.userMap.put(user.getId(), user);
        return Mono.just(user);
    }

    public Mono<User> delete(Long id) {
        return Mono.justOrEmpty(this.userMap.remove(id));
    }

}
