package com.eckmo.experimental.domain.dto;

import com.eckmo.experimental.domain.account.Account;
import com.eckmo.experimental.domain.address.Address;
import com.eckmo.experimental.domain.personal.PersonalInfos;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@Jacksonized
@Builder
public class InfosUsersOutput implements Serializable {
    private PersonalInfos infos;
    private Address address;
    private List<Account> activeAccounts;

    public InfosUsersOutput() {

    }
}
