package com.website.didado.domain.member.repository;

import com.website.didado.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    long countByEmail(String email);

    Optional<Member> findByUsernameAndEmail(String username, String email);
}
