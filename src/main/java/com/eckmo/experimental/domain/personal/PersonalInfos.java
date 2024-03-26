package com.eckmo.experimental.domain.personal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Jacksonized
@Builder
public class PersonalInfos implements Serializable {
    private String identityCardNumber;
    private String firstName;
    private String lastName;
    private Integer age;

}
