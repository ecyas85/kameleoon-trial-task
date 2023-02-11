package com.kameleoon.service;

import com.kameleoon.dto.VoteDto;
import com.kameleoon.exception.QuoteNoteFoundException;
import com.kameleoon.model.Quote;
import com.kameleoon.model.Vote;
import com.kameleoon.repository.QuoteRepository;
import com.kameleoon.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.kameleoon.model.enums.VoteType.UP_VOTE;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final QuoteRepository quoteRepository;

    @Transactional
    public void vote(VoteDto voteDto) {
        var quote = quoteRepository.findById(voteDto.quoteId())
                .orElseThrow(() -> new QuoteNoteFoundException("Quote with id: %s not found".formatted(voteDto.quoteId())));

        if (UP_VOTE.equals(voteDto.voteType())) {
            quote.upVote();
        } else {
            quote.downVote();
        }
        voteRepository.save(mapToVote(voteDto, quote));
        quoteRepository.save(quote);
    }

    private Vote mapToVote(VoteDto voteDto, Quote quote) {
        return Vote.builder()
                .voteType(voteDto.voteType())
                .quote(quote)
                .userAccount(quote.getPostedBy())
                .build();
    }
}
