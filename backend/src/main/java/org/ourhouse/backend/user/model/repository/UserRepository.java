package org.ourhouse.backend.user.model.repository;

import org.ourhouse.backend.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);

    Optional<User> findByEmail(String email);
}
