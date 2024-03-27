package com.eckmo.experimental.webflux;

import com.eckmo.experimental.domain.account.Account;
import com.eckmo.experimental.domain.account.AccountPredicates;
import com.eckmo.experimental.domain.account.AccountSupplier;
import com.eckmo.experimental.domain.address.Address;
import com.eckmo.experimental.domain.dto.InfoUserInput;
import com.eckmo.experimental.domain.dto.InfosUsersOutput;
import com.eckmo.experimental.domain.personal.PersonalInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static com.eckmo.experimental.threadconf.ThreadUtils.sleep;

@Service
public class WebfluxService {

    @Autowired
    private ExecutorService accountExecutor;


    public Mono<InfosUsersOutput> getInfoUser(InfoUserInput input) {
        return this.parallelAsyncApiCall(input);
    }

    private List<Account> getAccountInfosAsync(InfoUserInput input) {

        sleep(500);
        System.out.println("executing getAccountInfosAsync with Thread : " + Thread.currentThread().getName());
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        return AccountSupplier.allAccounts.get().parallelStream().filter(account -> AccountPredicates.userAccount(input.getIdentityCardNumber()).test(account)).collect(Collectors.toList());

    }


    private Address getAddressInfosAsync(InfoUserInput input) {
        sleep(500);
        System.out.println("executing getAddressInfosAsync with Thread : " + Thread.currentThread().getName());
        return new Address("Greenwood", "20091", "Bronx", "WASHINGTON DC");
    }


    private PersonalInfos getPersonalInfosAsync(InfoUserInput input) {
        sleep(500);
        System.out.println("executing getPersonalInfosAsync with Thread : " + Thread.currentThread().getName());
        return new PersonalInfos("XN9987", "Will", "FERELL", 29);
    }

    private Mono<InfosUsersOutput> parallelAsyncApiCall(InfoUserInput input) {
        Mono<List<Account>> operation1 = Mono.defer(() -> Mono.just(getAccountInfosAsync(input))).subscribeOn(Schedulers.fromExecutor(accountExecutor));
        Mono<PersonalInfos> operation2 = Mono.defer(() -> Mono.just(getPersonalInfosAsync(input))).subscribeOn(Schedulers.fromExecutor(accountExecutor));
        Mono<Address> operation3 = Mono.defer(() -> Mono.just(getAddressInfosAsync(input))).subscribeOn(Schedulers.fromExecutor(accountExecutor));

        return Mono.zip(operation1, operation2, operation3)
                .map(tuple -> new InfosUsersOutput(tuple.getT2(), tuple.getT3(), tuple.getT1()));
        //.onErrorResume(throwable -> Mono.just(new InfosUsersOutput()));


    }
}
