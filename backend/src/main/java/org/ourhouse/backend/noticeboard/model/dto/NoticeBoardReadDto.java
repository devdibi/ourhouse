package org.ourhouse.backend.noticeboard.model.dto;

import lombok.Builder;
import lombok.Data;
import org.ourhouse.backend.noticeboard.model.entity.NoticeBoard;

public class NoticeBoardReadDto {
    @Builder
    @Data
    public static class Response{
        private boolean owner;
        private NoticeBoard board;
    }
}
