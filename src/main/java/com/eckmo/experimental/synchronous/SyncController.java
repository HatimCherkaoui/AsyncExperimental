package com.eckmo.experimental.synchronous;


import com.eckmo.experimental.domain.dto.InfoUserInput;
import com.eckmo.experimental.domain.dto.InfosUsersOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/sync")
public class SyncController {

    @Autowired
    private SyncService service;

    @GetMapping
    public InfosUsersOutput getInfoUser(@RequestBody InfoUserInput input) throws ExecutionException, InterruptedException {

        return service.getInfoUser(input);
    }
}
