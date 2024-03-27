package com.eckmo.experimental.webflux;

import com.eckmo.experimental.domain.dto.InfoUserInput;
import com.eckmo.experimental.domain.dto.InfosUsersOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webflux")
public class WebfluxController {

    @Autowired
    private WebfluxService service;

    @GetMapping
    public Mono<InfosUsersOutput> getInfoUser(@RequestBody InfoUserInput input) {
        return service.getInfoUser(input);
    }
}
