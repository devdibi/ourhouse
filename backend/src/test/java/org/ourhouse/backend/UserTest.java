package org.ourhouse.backend;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ourhouse.backend.user.model.dto.SignInDto;
import org.ourhouse.backend.user.model.dto.SignUpDto;
import org.ourhouse.backend.user.model.entity.User;
import org.ourhouse.backend.user.model.repository.UserRepository;
import org.ourhouse.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
@Slf4j
@ActiveProfiles("test")
public class UserTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("[signUp] 회원가입 테스트")
    public void signUpTest(){

        SignUpDto.Request admin = SignUpDto.Request.builder().email("test_admin@gmail.com").password("test_admin").name("test_admin").gender(1).age(20).role("admin").build();
        SignUpDto.Request user = SignUpDto.Request.builder().email("test_user@gmail.com").password("test_user").name("test_user").gender(2).age(30).role("user").build();

        userService.signUp(admin);
        userService.signUp(user);

        Optional<User> savedAdmin = userRepository.findByEmail(admin.getEmail());
        Optional<User> savedUser = userRepository.findByEmail(user.getEmail());

        if(savedAdmin.isPresent()) { log.info("관리자 회원 가입 완료 : {}", savedAdmin.get().getEmail()); }
        if(savedUser.isPresent()) { log.info("유저 회원 가입 완료 : {}", savedUser.get().getEmail()); }
    }

    @Test
    @DisplayName("[signIn] 로그인 테스트")
    public void signInTest(){
        User before_admin = User.builder().email("signin_admin@gmail.com").password(passwordEncoder.encode("signin_admin")).name("signin_admin").gender(1).age(20).roles(Collections.singletonList("ROLE_ADMIN")).build();
        User before_user = User.builder().email("signin_user@gmail.com").password(passwordEncoder.encode("signin_user")).name("signin_user").gender(1).age(20).roles(Collections.singletonList("ROLE_ADMIN")).build();

        userRepository.save(before_admin);
        userRepository.save(before_user);

        SignInDto.Request admin = SignInDto.Request.builder().email("signin_admin@gmail.com").password("signin_admin").build();
        SignInDto.Request user = SignInDto.Request.builder().email("signin_user@gmail.com").password("signin_user").build();

        log.info((String) userService.signIn(admin).getData());
        log.info((String) userService.signIn(user).getData());
    }

}
