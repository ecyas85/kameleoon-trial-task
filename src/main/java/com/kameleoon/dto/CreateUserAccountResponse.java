package com.kameleoon.dto;

import java.time.ZonedDateTime;

public record CreateUserAccountResponse(
        Long id,
        String username,
        String email,
        ZonedDateTime createdAt,
        boolean enabled) {
}
