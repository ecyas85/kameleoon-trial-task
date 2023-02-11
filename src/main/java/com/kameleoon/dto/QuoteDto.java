package com.kameleoon.dto;

public record QuoteDto(
        Long id,
        String quoteName,
        Integer voteCount,
        UserAccountDto postedBy) {
}
