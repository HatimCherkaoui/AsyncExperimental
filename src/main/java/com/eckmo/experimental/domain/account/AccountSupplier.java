package com.eckmo.experimental.domain.account;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.function.Supplier;

public class AccountSupplier<T extends Account> {
    public static Supplier<Account> newAccount = Account::new;

    public static Supplier<List<Account>> allAccounts = () -> List.of(
            new Account(true, "ACX-1111-111-1111", 121345.98, LocalDateTime.of(2022, Month.APRIL, 16, 0, 0, 0), "XV-12131"),
            new Account(true, "XXX-2222-222-2222", 2009.98, LocalDateTime.of(2023, Month.JUNE, 1, 0, 0, 0), "XV-12131"),
            new Account(true, "ACX-3333-333-3333", 0.00, LocalDateTime.of(2022, Month.SEPTEMBER, 8, 0, 0, 0), "XV-12131"),
            new Account(true, "ACX-4444-444-4444", 879.98, LocalDateTime.of(2014, Month.MARCH, 7, 0, 0, 0), "XV-45454")
    );


}
