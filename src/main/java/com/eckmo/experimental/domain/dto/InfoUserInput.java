package com.eckmo.experimental.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;

@Data
@Jacksonized
@Builder
public class InfoUserInput implements Serializable {
    @JsonProperty("identityCardNumber")
    private String identityCardNumber;
}
