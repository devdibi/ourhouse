package org.ourhouse.backend.user.model.dto;

import lombok.Getter;

public class SignUpDto {

    @Getter
    public static class Request{
        private String email;
        private String password;
        private String name;
        private Integer gender;
        private Integer age;
        private String role;
    }
}
