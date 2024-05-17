package org.ourhouse.backend.noticeboard.model.repository;

import org.ourhouse.backend.noticeboard.model.entity.NoticeBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {
}
