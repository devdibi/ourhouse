package org.ourhouse.backend.freeboard.model.dto;

import lombok.Builder;
import lombok.Data;
import org.ourhouse.backend.freeboard.model.entity.FreeBoard;


public class FreeBoardReadDto {

    @Builder
    @Data
    public static class Response{
        private boolean owner;
        private FreeBoard board;
    }
}
