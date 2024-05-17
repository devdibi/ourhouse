package org.ourhouse.backend.noticeboard.model.dto;

import lombok.Builder;
import lombok.Getter;

public class NoticeBoardCreateDto {

    @Getter
    @Builder
    public static class Request{
        private String title;
        private String content;
    }
}
