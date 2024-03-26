package com.eckmo.experimental.asynchronous;

import com.eckmo.experimental.domain.dto.InfoUserInput;
import com.eckmo.experimental.domain.dto.InfosUsersOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    private AsyncService service;

    @Autowired
    private ExecutorService infoExecutor;

    @GetMapping
    public InfosUsersOutput getInfoUser(@RequestBody InfoUserInput input) throws ExecutionException, InterruptedException {
        return service.getInfoUser(input);
    }
}
