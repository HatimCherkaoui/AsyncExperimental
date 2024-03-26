package com.eckmo.experimental.synchronous;

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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

import static com.eckmo.experimental.threadconf.ThreadUtils.sleep;

@Service
public class SyncService {
    @Autowired
    private ExecutorService accountExecutor;


    private List<Account> processResult(List<Account> accounts) {
        AccountFunctions.setInfoExecutor(accountExecutor);
        return accounts.stream().parallel()
                .filter(AccountPredicates.isAccountValid)
                .collect(Collectors.toList());
    }

    public InfosUsersOutput getInfoUser(InfoUserInput input) {
        return this.parallelAsyncApiCall(input);
    }

    private InfosUsersOutput parallelAsyncApiCall(InfoUserInput input) {
        PersonalInfos personalInfos = getPersonalInfosAsync(input);
        Address address = getAddressInfosAsync(input);
        List<Account> accounts = getAccountInfosAsync(input);
        InfosUsersOutput output = new InfosUsersOutput();
        output.setActiveAccounts(processResult(accounts));
        output.setAddress(address);
        output.setInfos(personalInfos);


        return output;
    }

    @Async("infoExecutor")
    private List<Account> getAccountInfosAsync(InfoUserInput input) {

        sleep(500);
        System.out.println("executing getAccountInfosAsync with Thread : " + Thread.currentThread().getName());
        final LocalDateTime from = LocalDateTime.of(2014, Month.APRIL, 16, 0, 0, 0);
        return AccountSupplier.allAccounts.get().stream().filter(account -> AccountPredicates.userAccount(input.getIdentityCardNumber()).test(account)).collect(Collectors.toList());

    }

    @Async("infoExecutor")
    private Address getAddressInfosAsync(InfoUserInput input) {
        sleep(500);
        System.out.println("executing getAddressInfosAsync with Thread : " + Thread.currentThread().getName());
        return new Address("Greenwood", "20091", "Bronx", "WASHINGTON DC");
    }

    @Async("infoExecutor")
    private PersonalInfos getPersonalInfosAsync(InfoUserInput input) {
        sleep(500);
        System.out.println("executing getPersonalInfosAsync with Thread : " + Thread.currentThread().getName());
        return new PersonalInfos("XN9987", "Will", "FERELL", 29);
    }
}
