package org.ourhouse.backend.noticeboard.model.dto;

import lombok.Builder;
import lombok.Data;

public class NoticeBoardUpdateDto {

    @Data
    @Builder
    public static class Request{
        private String title;
        private String content;
    }
}
