package org.ourhouse.backend.common.utils;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtils {

    public static <T> ResponseEntity<T> success(T response) {return ResponseEntity.status(200).body(response);}

    public static <T> ResponseEntity<T> error(T response, HttpStatus status) {return ResponseEntity.status(status).body(response); }

    public static ResponseEntity<?> error(Throwable throwable) {return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(throwable); }

    public static ResponseEntity<?> error(Throwable throwable, HttpStatus status) {return ResponseEntity.status(status).body(throwable); }

    public static ResponseEntity<?> error(String message, HttpStatus status) {return ResponseEntity.status(status).body(message); }

    @Builder
    @Getter
    public static class Response{
        private boolean success;
        private HttpStatus code;
        private String msg;
        private Object data;

        public static Response success(String msg, Object data) {return Response.builder().success(true).code(HttpStatus.OK).msg(msg).data(data).build();}

        public static Response error(String msg, HttpStatus status) {return Response.builder().success(false).code(status).msg(msg).build(); }
    }

}