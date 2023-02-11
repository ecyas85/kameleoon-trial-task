package com.kameleoon.controller;

import com.kameleoon.dto.CreateQuoteRequest;
import com.kameleoon.dto.CreateQuoteResponse;
import com.kameleoon.dto.GetQuoteResponse;
import com.kameleoon.dto.GetQuotesResponse;
import com.kameleoon.dto.QuoteModifyRequest;
import com.kameleoon.dto.QuoteModifyResponse;
import com.kameleoon.facade.QuoteFacade;
import com.kameleoon.service.QuoteService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/quotes")
public record QuoteController(QuoteFacade quoteFacade,
                              QuoteService quoteService) {

    @GetMapping
    public ResponseEntity<GetQuotesResponse> getTop10Quotes() {
        log.info("'QuoteController': getting all quotes");
        return ResponseEntity.ok()
                .body(quoteFacade.getTop10Quotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetQuoteResponse> getQuoteDetails(@PathVariable Long id) {
        log.info("'QuoteController': getting quote details by id: {}", id);
        return ResponseEntity.ok()
                .body(quoteFacade.getQuoteDetails(id));
    }

    @PostMapping
    public ResponseEntity<CreateQuoteResponse> createQuote(@Valid @RequestBody CreateQuoteRequest createQuoteRequest) {
        log.info("'QuoteController': New quote creation {}", createQuoteRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(quoteFacade.createQuote(createQuoteRequest));
    }

    @PatchMapping("/modify")
    public ResponseEntity<QuoteModifyResponse> modifyQuote(@RequestBody QuoteModifyRequest request) {
        log.info("'QuoteController': trying to modify quote with id: {}", request.id());
        return ResponseEntity.status(HttpStatus.OK)
                .body(quoteFacade.modify(request));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteQuote(@PathVariable Long id) {
        log.info("'QuoteController': Delete quote with id: {}", id);
        quoteService.deleteQuote(id);
    }
}
