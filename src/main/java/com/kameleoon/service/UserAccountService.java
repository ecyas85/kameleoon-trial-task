package com.kameleoon.service;

import com.kameleoon.model.UserAccount;
import com.kameleoon.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    @Transactional
    public UserAccount createUserAccount(UserAccount userAccount) {
        return userAccountRepository.saveAndFlush(userAccount);
    }

    @Transactional
    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }
}
