package org.ourhouse.backend.user.controller;

import lombok.AllArgsConstructor;
import org.ourhouse.backend.common.utils.ApiUtils;
import org.ourhouse.backend.user.model.dto.SignInDto;
import org.ourhouse.backend.user.model.dto.SignUpDto;
import org.ourhouse.backend.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.ourhouse.backend.common.utils.ApiUtils.error;
import static org.ourhouse.backend.common.utils.ApiUtils.success;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<? extends Object> signUp(@RequestBody SignUpDto.Request request) {
        try{
            ApiUtils.Response response = userService.signUp(request);
            if(response.isSuccess()) return success(response);
            return error(response, response.getCode());
        }catch (Exception e){
            return error(e);
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? extends Object> signIn(@RequestBody SignInDto.Request request){
        try{
            ApiUtils.Response response = userService.signIn(request);
            if(response.isSuccess()) return success(response);
            return error(response, response.getCode());
        }catch (Exception e){
            return error(e);
        }
    }

}
