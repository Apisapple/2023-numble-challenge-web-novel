package com.example.myseries.member.repository;

import com.example.myseries.member.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  boolean existsByEmail(String email);

  Optional<Member> findByEmail(String email);

  Optional<Member> findByName(String name);

  List<Member> findByNameContaining(String name);
}
