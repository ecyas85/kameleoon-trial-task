package com.kameleoon.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateQuoteRequest(
        @NotNull
        String quoteName,
        Integer voteCount,
        UserAccountDto userAccount,
        LocalDate createdAt) {
}
