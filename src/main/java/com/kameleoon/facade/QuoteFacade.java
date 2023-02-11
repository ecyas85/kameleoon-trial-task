package com.kameleoon.facade;

import com.kameleoon.dto.CreateQuoteRequest;
import com.kameleoon.dto.CreateQuoteResponse;
import com.kameleoon.dto.GetQuoteResponse;
import com.kameleoon.dto.GetQuotesResponse;
import com.kameleoon.dto.QuoteModifyRequest;
import com.kameleoon.dto.QuoteModifyResponse;
import com.kameleoon.mapper.QuoteMapper;
import com.kameleoon.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuoteFacade {

    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;

    public GetQuotesResponse getTop10Quotes() {
        return quoteMapper.toGetAllQuotesResponse(quoteService.getTop10Quotes());
    }

    public CreateQuoteResponse createQuote(CreateQuoteRequest createQuoteRequest) {
        var quote = quoteMapper.toQuote(createQuoteRequest);
        return quoteMapper.toCreateQuoteResponse(quoteService.createQuote(quote));
    }

    public GetQuoteResponse getQuoteDetails(Long id) {
        var quote = quoteService.getQuoteById(id);
        return new GetQuoteResponse(quoteMapper.toQuoteDto(quote));
    }

    public QuoteModifyResponse modify(QuoteModifyRequest request) {
        return new QuoteModifyResponse(quoteMapper.toQuoteDto(quoteService.updateQuote(request)));
    }
}
