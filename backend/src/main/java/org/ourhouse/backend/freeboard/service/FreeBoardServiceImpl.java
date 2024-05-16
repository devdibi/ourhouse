package org.ourhouse.backend.freeboard.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ourhouse.backend.common.utils.ApiUtils;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardCreateDto;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardReadDto;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardUpdateDto;
import org.ourhouse.backend.freeboard.model.entity.FreeBoard;
import org.ourhouse.backend.freeboard.model.repository.FreeBoardRepository;
import org.ourhouse.backend.user.model.entity.User;
import org.ourhouse.backend.user.model.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService {

    private FreeBoardRepository freeBoardRepository;
    private UserRepository userRepository;

    @Override
    public ApiUtils.Response create(FreeBoardCreateDto.Request request, String email) {
        // 유저 정보 로드
        User user = userRepository.findByEmail(email).orElseThrow(() -> new NoSuchElementException("회원정보가 존재하지 않습니다."));

        FreeBoard board = FreeBoard.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user.getName())
                .user(user)
                .views(0)
                .build();

        freeBoardRepository.save(board);

        return ApiUtils.Response.success("성공적으로 게시글을 작성했습니다.", board);
    }

    @Override
    public ApiUtils.Response list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<FreeBoard> list = freeBoardRepository.findAll(pageable);
        return ApiUtils.Response.success("성공적으로 게시글 목록을 조회했습니다.", list.getContent());
    }

    @Override
    public ApiUtils.Response read(String email, Long id) {
        Optional<FreeBoard> optionalFreeBoard = freeBoardRepository.findById(id);

        if(optionalFreeBoard.isEmpty()){
            return ApiUtils.Response.error("게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        FreeBoard freeBoard = optionalFreeBoard.get();

        if(freeBoard.getUser().getEmail().equals(email)){
            return ApiUtils.Response.success("성공적으로 게시글을 조회했습니다.", FreeBoardReadDto.Response.builder().owner(true).board(freeBoard).build());
        }

        // 조회수 추가
        freeBoard.setViews(freeBoard.getViews()+1);

        // DB 수정
        freeBoardRepository.save(freeBoard);

        return ApiUtils.Response.success("성공적으로 게시글을 조회했습니다.", FreeBoardReadDto.Response.builder().owner(false).board(freeBoard).build());
    }

    @Override
    public ApiUtils.Response update(FreeBoardUpdateDto.Request request, Long id, String email) {
        Optional<FreeBoard> optionalFreeBoard = freeBoardRepository.findById(id);

        if(optionalFreeBoard.isEmpty()){
            return ApiUtils.Response.error("게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        if(!optionalFreeBoard.get().getUser().getEmail().equals(email)) {
            return ApiUtils.Response.error("게시글의 작성자가 아닙니다.", HttpStatus.FORBIDDEN);
        }

        FreeBoard freeBoard = optionalFreeBoard.get();

        freeBoard.setTitle(request.getTitle());
        freeBoard.setContent(request.getContent());

        freeBoardRepository.save(freeBoard);

        return ApiUtils.Response.success("성공적으로 게시글을 수정했습니다.", freeBoard);
    }

    @Override
    @Transactional
    public ApiUtils.Response delete(String email, Long id) {
        Optional<FreeBoard> optionalFreeBoard = freeBoardRepository.findById(id);

        if (optionalFreeBoard.isEmpty()) {
            return ApiUtils.Response.error("게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        if (!optionalFreeBoard.get().getUser().getEmail().equals(email)) {
            return ApiUtils.Response.error("게시글의 작성자가 아닙니다.", HttpStatus.FORBIDDEN);
        }

        freeBoardRepository.deleteById(id);
        return ApiUtils.Response.success("성공적으로 게시글을 삭제했습니다.", null);
    }
}
