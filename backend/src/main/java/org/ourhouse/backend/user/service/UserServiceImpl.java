package org.ourhouse.backend.user.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ourhouse.backend.common.jwt.JwtTokenProvider;
import org.ourhouse.backend.common.utils.ApiUtils;
import org.ourhouse.backend.user.model.dto.SignInDto;
import org.ourhouse.backend.user.model.dto.SignUpDto;
import org.ourhouse.backend.user.model.entity.User;
import org.ourhouse.backend.user.model.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

import static org.ourhouse.backend.common.utils.ApiUtils.Response.error;
import static org.ourhouse.backend.common.utils.ApiUtils.Response.success;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ApiUtils.Response signUp(SignUpDto.Request request) {
        log.info("[signUp] 이메일 유효성 검증");

        // 이메일 존재 여부 파악
        Optional<User> searchUser = userRepository.findByEmail(request.getEmail());

        if(searchUser.isPresent()){
            return ApiUtils.Response.builder().code(HttpStatus.CONFLICT).success(false).msg("이미 존재하는 이메일입니다.").build();
        }

        // User entity 생성
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .gender(request.getGender())
                .age(request.getAge())
                .roles(Collections.singletonList(request.getRole().equalsIgnoreCase("admin") ? "ROLE_ADMIN" : "ROLE_USER"))
                .build();

        // 저장
        userRepository.save(user);

        log.info("[signUp] 회원가입 완료");

        return ApiUtils.Response.builder().success(true).code(HttpStatus.OK).msg("회원 가입 완료").build();
    }

    @Override
    public ApiUtils.Response signIn(SignInDto.Request request) {
        log.info("[signIn] 회원 정보 조회");

        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        if(user == null) {
            log.info("[signIn] 존재하지 않는 회원");
            return error("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND);
        }

        log.info("[signIn] 패스워드 비교");

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            log.info("[signIn] 비밀번호 불일치");
            return error("잘못된 비밀번호 입니다.", HttpStatus.BAD_REQUEST);
        }

        log.info("[signIn] 인증 완료");

        // 토큰 생성
        ApiUtils.Response response = success("성공적으로 로그인을 완료했습니다.", jwtTokenProvider.createToken(String.valueOf(user.getEmail()), user.getRoles()));
        log.info("[signIn] 토큰 생성 완료 : {}", response.getData());

        return response;
    }
}
