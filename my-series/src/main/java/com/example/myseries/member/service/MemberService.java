package com.example.myseries.member.service;

import com.example.myseries.member.dto.MemberDto;
import com.example.myseries.member.entity.Member;
import com.example.myseries.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  /**
   * Saved member
   *
   * @param name member's name
   * @return saved member
   */
  @Transactional
  public Member saveMember(String name) {
    Member member = Member.builder()
        .name(name)
        .build();
    return memberRepository.save(member);
  }


  /**
   * Get Member information by id
   *
   * @param id Member's ID
   * @return Member
   * @throws IllegalArgumentException 조회하지 못한 상황
   */
  public MemberDto getMemberById(Long id) throws IllegalArgumentException {
    Member savedMember = memberRepository.findById(id).orElseThrow(
        () -> new IllegalArgumentException("Cannot found member by id")
    );

    return savedMember.toDto();
  }

  /**
   * Get Member information by name
   *
   * @param name Member's ID
   * @return Member
   * @throws IllegalArgumentException 조회하지 못한 상황
   */
  public MemberDto getMemberByName(String name) throws IllegalArgumentException {
    Member savedMember = memberRepository.findByName(name).orElseThrow(
        () -> new IllegalArgumentException("Cannot found member by name")
    );

    return savedMember.toDto();
  }

  /**
   * buy point function
   *
   * @param name  member name
   * @param point mount of point
   * @return member information
   */
  @Transactional
  public MemberDto buyPoint(String name, Integer point) {
    Member savedMember = memberRepository.findByName(name).orElseThrow(
        () -> new IllegalArgumentException("Cannot found member by name")
    );

    savedMember.addPoint(point);

    return savedMember.toDto();
  }

  /**
   * use point function
   *
   * @param name  member name
   * @param point mount of point
   * @return member information
   */
  @Transactional
  public MemberDto usePoint(String name, Integer point) {
    Member savedMember = memberRepository.findByName(name).orElseThrow(
        () -> new IllegalArgumentException("Cannot found member by name")
    );

    savedMember.subPoint(point);

    return savedMember.toDto();
  }
}
