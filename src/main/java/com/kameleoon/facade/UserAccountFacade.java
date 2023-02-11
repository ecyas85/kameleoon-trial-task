package com.kameleoon.facade;

import com.kameleoon.dto.CreateUserAccountRequest;
import com.kameleoon.dto.CreateUserAccountResponse;
import com.kameleoon.mapper.UserAccountMapper;
import com.kameleoon.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAccountFacade {

    private final UserAccountMapper userAccountMapper;
    private final UserAccountService userAccountService;

    public CreateUserAccountResponse createUserAccount(CreateUserAccountRequest createUserAccountRequest) {
        var userAccount = userAccountMapper.toUserAccount(createUserAccountRequest);
        return userAccountMapper.toCreateUserAccountResponse(userAccountService.createUserAccount(userAccount));
    }
}
