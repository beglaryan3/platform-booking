package com.booking.platform.repository;

import com.booking.platform.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository  extends JpaRepository<UserEntity, UUID> {
    public UserEntity findByEmail(String email);
}
