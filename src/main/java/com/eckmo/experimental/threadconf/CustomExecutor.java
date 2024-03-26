package com.eckmo.experimental.threadconf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Configuration
@EnableAsync
public class CustomExecutor {
    @Bean(name = "infoExecutor")
    public ExecutorService infoExecutor() {
        //return new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        //return Executors.newWorkStealingPool();
        //return Executors.newCachedThreadPool();
        //return Executors.newCachedThreadPool();
        return threadPoolExecutor("infoExecutor");
        //return Executors.newCachedThreadPool(new CustomThreadFactory("infoExecutor"));
        //return Executors.newFixedThreadPool(7);
        //return Executors.newFixedThreadPool(8, new CustomThreadFactory("infoExecutor"));
    }

    @Bean(name = "addressExecutor")
    public ExecutorService addressExecutor() {
        //return new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        //return Executors.newWorkStealingPool();
        //return Executors.newCachedThreadPool();
        return threadPoolExecutor("addressExecutor");
        //return Executors.newCachedThreadPool(new CustomThreadFactory("addressExecutor"));
        //return Executors.newCachedThreadPool();
        //return Executors.newFixedThreadPool(8, new CustomThreadFactory("addressExecutor"));

    }

    @Bean(name = "accountExecutor")
    public ExecutorService accountExecutor() {
        //return new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        //return Executors.newWorkStealingPool();
        //return Executors.newCachedThreadPool();
        return threadPoolExecutor("accountExecutor");
        //return Executors.newCachedThreadPool(new CustomThreadFactory("accountExecutor"));
        //return Executors.newCachedThreadPool();
        //return Executors.newFixedThreadPool(8, new CustomThreadFactory("accountExecutor"));

    }

    @Bean(name = "checkActiveAccountExecutor")
    public ExecutorService checkActiveAccountExecutor() {
        //return new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors());
        //return Executors.newWorkStealingPool();
        //return Executors.newCachedThreadPool();
        return threadPoolExecutor("checkActiveAccountExecutor");
        //return Executors.newCachedThreadPool(new CustomThreadFactory("checkActiveAccountExecutor"));
        //return Executors.newCachedThreadPool();
        //return Executors.newFixedThreadPool(8, new CustomThreadFactory("checkActiveAccountExecutor"));

    }

    private ThreadPoolExecutor threadPoolExecutor(String poolName) {
        return new ThreadPoolExecutor(240,
                Integer.MAX_VALUE,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(),
                new CustomThreadFactory(poolName));
    }
}
