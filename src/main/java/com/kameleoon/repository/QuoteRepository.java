package com.kameleoon.repository;

import com.kameleoon.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote, Long> {

    List<Quote> findTop10ByOrderByVoteCountDesc();
}
