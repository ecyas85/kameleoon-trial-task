package com.kameleoon.dto;

import java.time.LocalDate;

public record CreateQuoteResponse(Long id,
                                  String quoteName,
                                  Integer voteCount,
                                  UserAccountDto postedBy,
                                  LocalDate createdAt) {
}
