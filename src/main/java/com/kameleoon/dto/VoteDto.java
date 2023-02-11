package com.kameleoon.dto;

import com.kameleoon.model.enums.VoteType;

public record VoteDto(VoteType voteType,
                      Long quoteId) {
}
