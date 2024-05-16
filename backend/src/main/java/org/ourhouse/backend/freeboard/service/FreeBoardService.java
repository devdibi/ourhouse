package org.ourhouse.backend.freeboard.service;

import org.ourhouse.backend.common.utils.ApiUtils;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardCreateDto;
import org.ourhouse.backend.freeboard.model.dto.FreeBoardUpdateDto;
import org.springframework.stereotype.Service;

@Service
public interface FreeBoardService {

    ApiUtils.Response create(FreeBoardCreateDto.Request request, String email);
    ApiUtils.Response list(int page, int size);
    ApiUtils.Response read(String email, Long id);
    ApiUtils.Response update(FreeBoardUpdateDto.Request request, Long id, String email);
    ApiUtils.Response delete(String email, Long id);
}
