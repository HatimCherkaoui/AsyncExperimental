package com.eckmo.experimental.domain.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Jacksonized
@Builder
public class Account implements Serializable {
    private boolean isActive;
    private String iban;
    private Double balence;
    private LocalDateTime dateCreated;
    private String ownerId;
}
