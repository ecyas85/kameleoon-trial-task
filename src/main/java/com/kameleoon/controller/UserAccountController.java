package com.kameleoon.controller;

import com.kameleoon.dto.CreateUserAccountRequest;
import com.kameleoon.dto.CreateUserAccountResponse;
import com.kameleoon.facade.UserAccountFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/users")
public record UserAccountController(UserAccountFacade userAccountFacade) {

    @PostMapping
    public ResponseEntity<CreateUserAccountResponse> createUserAccount(@RequestBody CreateUserAccountRequest createUserAccountRequest) {
        log.info("New user account creation {}", createUserAccountRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userAccountFacade.createUserAccount(createUserAccountRequest));
    }
}

