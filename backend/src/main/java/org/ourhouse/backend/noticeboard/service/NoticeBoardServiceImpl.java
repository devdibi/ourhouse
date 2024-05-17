package org.ourhouse.backend.noticeboard.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.ourhouse.backend.common.utils.ApiUtils;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardCreateDto;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardReadDto;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardUpdateDto;
import org.ourhouse.backend.freeboard.model.entity.FreeBoard;
import org.ourhouse.backend.freeboard.model.repository.FreeBoardRepository;
import org.ourhouse.backend.noticeboard.model.dto.NoticeBoardCreateDto;
import org.ourhouse.backend.noticeboard.model.dto.NoticeBoardReadDto;
import org.ourhouse.backend.noticeboard.model.dto.NoticeBoardUpdateDto;
import org.ourhouse.backend.noticeboard.model.entity.NoticeBoard;
import org.ourhouse.backend.noticeboard.model.repository.NoticeBoardRepository;
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
public class NoticeBoardServiceImpl implements NoticeBoardService {
    private NoticeBoardRepository noticeBoardRepository;
    private UserRepository userRepository;

    @Override
    public ApiUtils.Response create(NoticeBoardCreateDto.Request request, String email) {
        // 유저 정보 로드
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if(optionalUser.isEmpty()){
            return ApiUtils.Response.error("회원정보가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        User user = optionalUser.get();

        NoticeBoard board = NoticeBoard.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(user.getName())
                .user(user)
                .views(0)
                .build();

        noticeBoardRepository.save(board);

        return ApiUtils.Response.success("성공적으로 게시글을 작성했습니다.", board);
    }

    @Override
    public ApiUtils.Response list(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<NoticeBoard> list = noticeBoardRepository.findAll(pageable);
        return ApiUtils.Response.success("성공적으로 게시글 목록을 조회했습니다.", list.getContent());
    }

    @Override
    public ApiUtils.Response read(String email, Long id) {
        Optional<NoticeBoard> optionalNoticeBoard = noticeBoardRepository.findById(id);

        if(optionalNoticeBoard.isEmpty()){
            return ApiUtils.Response.error("게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        NoticeBoard noticeBoard = optionalNoticeBoard.get();

        if(noticeBoard.getUser().getEmail().equals(email)){
            return ApiUtils.Response.success("성공적으로 게시글을 조회했습니다.", NoticeBoardReadDto.Response.builder().owner(true).board(noticeBoard).build());
        }

        // 조회수 추가
        noticeBoard.setViews(noticeBoard.getViews()+1);

        // DB 수정
        noticeBoardRepository.save(noticeBoard);

        return ApiUtils.Response.success("성공적으로 게시글을 조회했습니다.", NoticeBoardReadDto.Response.builder().owner(false).board(noticeBoard).build());
    }

    @Override
    public ApiUtils.Response update(NoticeBoardUpdateDto.Request request, Long id, String email) {
        Optional<NoticeBoard> optionalNoticeBoard = noticeBoardRepository.findById(id);

        if(optionalNoticeBoard.isEmpty()){
            return ApiUtils.Response.error("게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        if(!optionalNoticeBoard.get().getUser().getEmail().equals(email)) {
            return ApiUtils.Response.error("게시글의 작성자가 아닙니다.", HttpStatus.FORBIDDEN);
        }

        NoticeBoard noticeBoard = optionalNoticeBoard.get();

        noticeBoard.setTitle(request.getTitle());
        noticeBoard.setContent(request.getContent());

        noticeBoardRepository.save(noticeBoard);

        return ApiUtils.Response.success("성공적으로 게시글을 수정했습니다.", noticeBoard);
    }

    @Override
    @Transactional
    public ApiUtils.Response delete(String email, Long id) {
        Optional<NoticeBoard> optionalNoticeBoard = noticeBoardRepository.findById(id);

        if (optionalNoticeBoard.isEmpty()) {
            return ApiUtils.Response.error("게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        if (!optionalNoticeBoard.get().getUser().getEmail().equals(email)) {
            return ApiUtils.Response.error("게시글의 작성자가 아닙니다.", HttpStatus.FORBIDDEN);
        }

        noticeBoardRepository.deleteById(id);
        return ApiUtils.Response.success("성공적으로 게시글을 삭제했습니다.", null);
    }
}
