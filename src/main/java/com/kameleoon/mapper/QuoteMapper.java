package com.kameleoon.mapper;

import com.kameleoon.dto.CreateQuoteRequest;
import com.kameleoon.dto.CreateQuoteResponse;
import com.kameleoon.dto.GetQuotesResponse;
import com.kameleoon.dto.QuoteDto;
import com.kameleoon.model.Quote;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuoteMapper {

    default GetQuotesResponse toGetAllQuotesResponse(List<Quote> quotes) {
        return new GetQuotesResponse(quotes.stream()
                .map(this::toQuoteDto)
                .toList());
    }

    QuoteDto toQuoteDto(Quote quote);

    Quote toQuote(CreateQuoteRequest createQuoteRequest);

    CreateQuoteResponse toCreateQuoteResponse(Quote quote);
}
