package org.ourhouse.backend.freeboard.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

public class FreeBoardUpdateDto {

    @Data
    @Builder
    public static class Request{
        private String title;
        private String content;
    }
}
