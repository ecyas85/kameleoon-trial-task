package com.kameleoon.mapper;

import com.kameleoon.dto.CreateUserAccountRequest;
import com.kameleoon.dto.CreateUserAccountResponse;
import com.kameleoon.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {
    @Mapping(target = "enabled", expression = "java(enableByDefault())")
    UserAccount toUserAccount(CreateUserAccountRequest createUserAccountRequest);

    default boolean enableByDefault() {
        return true;
    }

    CreateUserAccountResponse toCreateUserAccountResponse(UserAccount userAccount);
}
