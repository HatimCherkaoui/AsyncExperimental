package com.eckmo.experimental.asynchronous;

import com.eckmo.experimental.domain.account.Account;
import com.eckmo.experimental.domain.account.AccountFunctions;
import com.eckmo.experimental.domain.account.AccountPredicates;
import com.eckmo.experimental.domain.account.AccountSupplier;
import com.eckmo.experimental.domain.address.Address;
import com.eckmo.experimental.domain.dto.InfoUserInput;
import com.eckmo.experimental.domain.dto.InfosUsersOutput;
import com.eckmo.experimental.domain.personal.PersonalInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.eckmo.experimental.threadconf.ThreadUtils.sleep;

@Service
public class AsyncService {
    @Autowired
    private ExecutorService infoExecutor;
    @Autowired
    private ExecutorService accountExecutor;
    @Autowired
    private ExecutorService addressExecutor;


    //@Cacheable(value = "getInfoUser", key = "#input.identityCardNumber")
    public InfosUsersOutput getInfoUser(InfoUserInput input) throws ExecutionException, InterruptedException {
        return this.parallelAsyncApiCall(input);
    }

    private List<Account> processResult(List<Account> accounts) {
        AccountFunctions.setInfoExecutor(infoExecutor);
        return accounts.stream().parallel()
                .filter(AccountPredicates.isAccountValid)
                .collect(Collectors.toList());
    }

    private InfosUsersOutput parallelAsyncApiCall(InfoUserInput input) throws ExecutionException, InterruptedException {
        CompletableFuture<PersonalInfos> personalInfosCompletableFuture = getPersonalInfosAsync(input);
        CompletableFuture<Address> addressCompletableFuture = getAddressInfosAsync(input);
        CompletableFuture<List<Account>> accountCompletableFuture = getAccountInfosAsync(input);
        CompletableFuture<Void> future = CompletableFuture.allOf(accountCompletableFuture, addressCompletableFuture, personalInfosCompletableFuture);
        InfosUsersOutput output = new InfosUsersOutput();
        future.thenAcceptAsync(o -> {
            PersonalInfos personalInfos = personalInfosCompletableFuture.join();
            Address address = addressCompletableFuture.join();
            List<Account> accounts = accountCompletableFuture.join();

            output.setActiveAccounts(processResult(accounts));
            output.setAddress(address);
            output.setInfos(personalInfos);
            //return output;
        }).join();


        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            infoExecutor.shutdown();

        }));

        return output;
    }


    @Async("infoExecutor")
    private CompletableFuture<List<Account>> getAccountInfosAsync(InfoUserInput input) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            sleep(500);
            System.out.println("executing getAccountInfosAsync with Thread : " + Thread.currentThread().getName());
            return AccountSupplier.allAccounts.get().stream().filter(account -> AccountPredicates.userAccount(input.getIdentityCardNumber()).test(account)).collect(Collectors.toList());
        }, infoExecutor).orTimeout(30, TimeUnit.SECONDS);
    }

    @Async("infoExecutor")
    private CompletableFuture<Address> getAddressInfosAsync(InfoUserInput input) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task
            sleep(500);
            System.out.println("executing getAddressInfosAsync with Thread : " + Thread.currentThread().getName());
            return new Address("Greenwood", "20091", "Bronx", "WASHINGTON DC");
        }, infoExecutor).orTimeout(30, TimeUnit.SECONDS);
    }

    @Async("infoExecutor")
    private CompletableFuture<PersonalInfos> getPersonalInfosAsync(InfoUserInput input) {
        return CompletableFuture.supplyAsync(() -> {
            // Simulate a long-running task

            //TimeUnit.MILLISECONDS.sleep(600);
            sleep(500);
            System.out.println("executing getPersonalInfosAsync with Thread : " + Thread.currentThread().getName());


            return new PersonalInfos("XN9987", "Will", "FERELL", 29);
        }, infoExecutor).orTimeout(30, TimeUnit.SECONDS);
    }

}
