package com.IdentityService.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IdentityService.entity.UserCredentials;
import com.google.common.base.Optional;

public interface UserCredentialsRepo extends JpaRepository<UserCredentials, Integer> {

	java.util.Optional<UserCredentials> findByname(String username);

}
