package com.ArjunCode.springsecurityclient.repository;

import com.ArjunCode.springsecurityclient.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

}
