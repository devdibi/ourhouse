package org.ourhouse.backend.common.exception.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
public class AccessDeniedDto {
    private boolean success = false;
    private HttpStatus code = HttpStatus.FORBIDDEN;
    private String msg = "접근 권한이 없습니다.";
}
