package org.ourhouse.backend.user.service;

import org.ourhouse.backend.common.utils.ApiUtils;
import org.ourhouse.backend.user.model.dto.SignInDto;
import org.ourhouse.backend.user.model.dto.SignUpDto;


public interface UserService {

    ApiUtils.Response signUp(SignUpDto.Request request);

    ApiUtils.Response signIn(SignInDto.Request request);
}
