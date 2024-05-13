package org.ourhouse.backend.user.model.dto;

import lombok.Getter;

public class SignInDto {

    @Getter
    public static class Request{
        private String email;
        private String password;
    }
}
