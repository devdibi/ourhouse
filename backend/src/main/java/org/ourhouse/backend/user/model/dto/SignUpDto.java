package org.ourhouse.backend.user.model.dto;

import lombok.Builder;
import lombok.Getter;

public class SignUpDto {

    @Getter
    @Builder
    public static class Request{
        private String email;
        private String password;
        private String name;
        private Integer gender;
        private Integer age;
        private String role;
    }
}
