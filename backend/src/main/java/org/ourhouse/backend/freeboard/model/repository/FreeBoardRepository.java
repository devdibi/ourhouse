package org.ourhouse.backend.freeboard.model.repository;

import org.ourhouse.backend.freeboard.model.entity.FreeBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreeBoardRepository extends JpaRepository<FreeBoard, Long> {
}
