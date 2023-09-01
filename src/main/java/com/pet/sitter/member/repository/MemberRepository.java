package com.pet.sitter.member.repository;

import com.pet.sitter.common.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findBymemberId(String memberId);
}