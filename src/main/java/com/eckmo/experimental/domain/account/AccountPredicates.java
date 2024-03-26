package com.eckmo.experimental.domain.account;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.function.Predicate;

public class AccountPredicates<T extends Account> {


    public static Predicate<Account> isBallencePositive = account -> account.getBalence() > 0;
    public static Predicate<Account> createdInLast5Years = account -> account.getDateCreated().isAfter(LocalDateTime.of(2019, Month.JANUARY, 1, 0, 0, 0));
    public static Predicate<Account> isAccountActive = account -> AccountFunctions.isAccountActive.apply(account).join();
    public static Predicate<Account> isAccountValid = createdInLast5Years.and(isAccountActive);

    public static Predicate<Account> userAccount(String userId) {
        return account -> account.getOwnerId().equals(userId);
    }


}
