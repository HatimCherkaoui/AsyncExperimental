package com.eckmo.experimental.domain.address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@AllArgsConstructor
@Jacksonized
@Builder
public class Address implements Serializable {
    private String street;
    private String postalCode;
    private String city;
    private String state;

}
