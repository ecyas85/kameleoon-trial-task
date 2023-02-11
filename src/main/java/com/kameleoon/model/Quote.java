package com.kameleoon.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Table(name = "quote")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank(message = "Quote name can't be empty or null")
    private String quoteName;
    private Integer voteCount;
    @ManyToOne(fetch = FetchType.LAZY)
    private UserAccount postedBy;
    @CreationTimestamp
    private LocalDate createdAt;

    public void upVote() {
        this.setVoteCount(this.getVoteCount() + 1);
    }

    public void downVote() {
        this.setVoteCount(this.getVoteCount() - 1);
    }
}

