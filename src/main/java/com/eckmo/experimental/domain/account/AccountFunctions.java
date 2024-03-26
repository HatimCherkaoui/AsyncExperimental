package com.eckmo.experimental.domain.account;

import com.eckmo.experimental.domain.personal.PersonalInfos;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.eckmo.experimental.threadconf.ThreadUtils.sleep;


public class AccountFunctions<T extends Account> {
    public static BiFunction<List<Account>, PersonalInfos, List<Account>> findUserAccount = (accounts, personalInfos) -> accounts.parallelStream().filter(account -> account.getOwnerId().equals(personalInfos.getIdentityCardNumber())).collect(Collectors.toList());
    private static ExecutorService executorService;
    public static Function<Account, CompletableFuture<Boolean>> isAccountActive = account ->
            CompletableFuture.supplyAsync(() -> {
                // Simulate a long-running task
                sleep(200);
                System.out.println("executing checkActiveActive with Thread : " + Thread.currentThread().getName());
                return account.isActive();
            }, executorService);

    public static void setInfoExecutor(ExecutorService executorService) {
        AccountFunctions.executorService = executorService;
    }


}
