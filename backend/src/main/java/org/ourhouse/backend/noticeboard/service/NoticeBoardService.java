package org.ourhouse.backend.noticeboard.service;

import org.ourhouse.backend.common.utils.ApiUtils;
import org.ourhouse.backend.noticeboard.model.dto.NoticeBoardCreateDto;
import org.ourhouse.backend.noticeboard.model.dto.NoticeBoardUpdateDto;

public interface NoticeBoardService {

    ApiUtils.Response create(NoticeBoardCreateDto.Request request, String email);
    ApiUtils.Response list(int page, int size);
    ApiUtils.Response read(String email, Long id);
    ApiUtils.Response update(NoticeBoardUpdateDto.Request request, Long id, String email);
    ApiUtils.Response delete(String email, Long id);
}
