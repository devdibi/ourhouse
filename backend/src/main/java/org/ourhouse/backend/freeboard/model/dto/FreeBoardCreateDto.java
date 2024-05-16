package org.ourhouse.backend.freeboard.model.dto;


import lombok.Builder;
import lombok.Getter;

public class FreeBoardCreateDto {

    @Getter
    @Builder
    public static class Request{
        private String title;
        private String content;
    }
}
