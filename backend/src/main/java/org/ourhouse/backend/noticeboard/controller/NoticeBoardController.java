package org.ourhouse.backend.noticeboard.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ourhouse.backend.common.utils.ApiUtils;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardCreateDto;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardUpdateDto;
import org.ourhouse.backend.noticeboard.model.dto.NoticeBoardCreateDto;
import org.ourhouse.backend.noticeboard.model.dto.NoticeBoardUpdateDto;
import org.ourhouse.backend.noticeboard.service.NoticeBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.ourhouse.backend.common.utils.ApiUtils.error;
import static org.ourhouse.backend.common.utils.ApiUtils.success;

@RestController
@RequestMapping("/notice-board")
@AllArgsConstructor
@Slf4j
public class NoticeBoardController {

    private final NoticeBoardService noticeBoardService;

    // Create : 작성
    @PostMapping("/create")
    public ResponseEntity<? extends Object> create(@RequestBody NoticeBoardCreateDto.Request request, Principal principal){
        try{
            ApiUtils.Response response = noticeBoardService.create(request, principal.getName());
            if(response.isSuccess()) return success(response);
            return error(response, response.getCode());
        }catch (Exception e){
            log.info("[freeBoard_create] 게시글 작성 : {}", e.getMessage());
            return error(e);
        }
    }
    // Read : 리스트
    @GetMapping("/list")
    public ResponseEntity<? extends Object> list(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        try{
            ApiUtils.Response response = noticeBoardService.list(page, size);
            if(response.isSuccess()) return success(response);
            return error(response, response.getCode());
        }catch (Exception e){
            log.info("[freeBoard_list] 예기치 못한 오류 : {} ", e.getMessage());
            return error(e);
        }
    }

    // Read : 상세
    @GetMapping("/read")
    public ResponseEntity<? extends Object> read(@RequestParam(defaultValue = "1") long id, Principal principal){
        try{
            ApiUtils.Response response = noticeBoardService.read(principal.getName(), id);
            if(response.isSuccess()) return success(response);
            return error(response, response.getCode());
        }catch (Exception e){
            log.info("[freeBoard_read] 예기치 못한 오류 : {} ", e.getMessage());
            return error(e);
        }
    }
    // Update : 수정
    @PutMapping("/update")
    public ResponseEntity<? extends Object> update(@RequestBody NoticeBoardUpdateDto.Request request, @RequestParam(defaultValue = "1") long id, Principal principal){
        try{
            ApiUtils.Response response = noticeBoardService.update(request, id, principal.getName());
            if(response.isSuccess()) return success(response);
            return error(response, response.getCode());
        }catch (Exception e){
            log.info("[freeBoard_read] 예기치 못한 오류 : {} ", e.getMessage());
            return error(e);
        }
    }



    // Delete : 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<? extends Object> delete(@RequestParam(defaultValue = "1") long id, Principal principal){
        try{
            ApiUtils.Response response = noticeBoardService.delete(principal.getName(), id);
            if(response.isSuccess()) return success(response);
            return error(response, response.getCode());
        }catch (Exception e){
            log.info("[freeBoard_delete] 예기치 못한 오류 : {} ", e.getMessage());
            return error(e);
        }
    }
}
