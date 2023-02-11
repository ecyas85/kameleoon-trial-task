package com.kameleoon.dto;

import java.util.List;

public record GetQuotesResponse(List<QuoteDto> quotes) {
}
