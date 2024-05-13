package org.ourhouse.backend.user.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "favorite_area")
public class FavoriteArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 시도
    @Column(name = "sido", updatable = false, nullable = false)
    private String sido;

    // 시군구
    @Column(name = "sigungu", updatable = false, nullable = false)
    private String sigungu;

    // 읍면동
    @Column(name = "dong", updatable = false, nullable = false)
    private String dong;

    @ManyToOne
    private User user;

}
