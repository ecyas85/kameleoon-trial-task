package com.kameleoon.service;

import com.kameleoon.dto.QuoteModifyRequest;
import com.kameleoon.exception.KameleoonTrialTaskException;
import com.kameleoon.exception.QuoteNoteFoundException;
import com.kameleoon.model.Quote;
import com.kameleoon.model.UserAccount;
import com.kameleoon.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuoteService {

    private final QuoteRepository repository;
    private final UserAccountService userAccountService;

    @Transactional
    public List<Quote> getTop10Quotes() {
        return repository.findTop10ByOrderByVoteCountDesc();
    }

    @SneakyThrows
    @Transactional
    public Quote createQuote(Quote quote) {
        var allUserAccounts = userAccountService.getAllUserAccounts();
        assignRandomPublisher(quote, allUserAccounts);
        return repository.save(quote);
    }

    private void assignRandomPublisher(Quote quote, List<UserAccount> allUserAccounts) throws NoSuchAlgorithmException {
        Random random = SecureRandom.getInstanceStrong();
        if (allUserAccounts.isEmpty()) {
            log.error("Create user account first!");
            throw new KameleoonTrialTaskException("Create user account(s) first!");
        } else {
            var userAccount = allUserAccounts.get(random.nextInt(allUserAccounts.size()));
            quote.setPostedBy(userAccount);
        }
    }

    @Transactional
    public void deleteQuote(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public Quote getQuoteById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new QuoteNoteFoundException("Quote with id: %s not found".formatted(id)));
    }

    @Transactional
    public Quote updateQuote(QuoteModifyRequest request) {
        var quote = repository.findById(request.id())
                .orElseThrow(() -> new QuoteNoteFoundException("Quote with id: %s not found".formatted(request.id())));
        quote.setQuoteName(request.quoteName());
        return quote;
    }
}
