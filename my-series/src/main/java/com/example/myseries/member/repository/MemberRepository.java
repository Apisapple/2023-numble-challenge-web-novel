package com.example.myseries.member.repository;

import com.example.myseries.member.model.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByName(String name);

  List<Member> findByNameContaining(String name);
}
