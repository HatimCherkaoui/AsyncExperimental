package com.eckmo.experimental.webflux;

import com.eckmo.experimental.domain.account.Account;
import com.eckmo.experimental.domain.address.Address;
import com.eckmo.experimental.domain.personal.PersonalInfos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;
import reactor.core.publisher.Mono;

import java.util.List;

@Data
@AllArgsConstructor
@Jacksonized
@Builder
public class InfosUsersOutputReactive {
    private Mono<PersonalInfos> infos;
    private Mono<Address> address;
    private Mono<List<Account>> activeAccounts;

    public InfosUsersOutputReactive() {

    }

}
