package com.eckmo.experimental.domain.account;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AccountConsumers<T extends Account> {
    public static Consumer<Account> desactivateAccount = account -> account.setActive(false);
    public static Consumer<Account> deleteAccount = accountToDelete -> AccountSupplier.allAccounts.get().remove(AccountSupplier.allAccounts.get().indexOf(accountToDelete));
    public static Consumer<List<Account>> deleteAccounts = accountToDeleteList -> accountToDeleteList.parallelStream().forEach(account -> accountToDeleteList.remove(accountToDeleteList.indexOf(account)));
    public static Consumer<Supplier<List<Account>>> deleteOldAccounts = listSupplier -> listSupplier.get().parallelStream().filter(AccountPredicates.createdInLast5Years.negate()).forEach(deleteAccount);

}
