package org.ourhouse.backend.user.model.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    // 이메일
    @Column(name="email", nullable = false, unique = true, updatable = false)
    private String email;

    // 해싱 처리 알고리즘 구현해야함 => 우테캠 코드 참고하자
    // 비밀번호
    @Column(name="password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    // 이름
    @Column(name="name", nullable = false, updatable = false)
    private String name;

    // 성별
    @Column(name="gender", updatable = false)
    private Integer gender;

    // 나이
    @Column(name="age", updatable = false)
    private Integer age;

    // 가입 날짜
    @Column(name="created_at", updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    // 관심지역 리스트
    @OneToMany(mappedBy = "user")
    private List<FavoriteArea> favoriteAreaList;

    // role

    // 역할을 담은 리스트
    @ElementCollection(fetch = FetchType.EAGER) // 엔터티에 속하지 않는 컬렉션
    @Builder.Default // 객체 생성 시 기본값을 설정할 수 있음 => 리스트를 기본값으로 제공
    private List<String> roles = new ArrayList<>();

    // UserDetails

    // 계정의 권한 리스트
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    // 계정의 이름을 리턴
    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getUsername() {
        return this.email;
    }

    // 계정이 만료 됐는지 확인 => true는 만료되지 않았다는 의미
    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨 있는지 확인 => true는 잠기지 않았다는 의미
    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되었는지 반환 => true는 만료되지 않았다는 의미
    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정의 활성화 여부 => true는 활성화 상태
    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isEnabled() {
        return true;
    }
}
