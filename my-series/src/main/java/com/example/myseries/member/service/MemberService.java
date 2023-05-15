package com.example.myseries.member.service;

import com.example.myseries.member.dto.MemberDto;
import com.example.myseries.member.entity.Member;
import com.example.myseries.member.error.MemberError;
import com.example.myseries.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  /***
   * Save member function
   * @param memberDto member 정보
   * @return 저장된 멤버의 정보
   */
  @Transactional
  public MemberDto saveMember(MemberDto memberDto) {
    Member member = memberDto.toEntity();

    if (checkAlreadyMember(member)) {
      throw new IllegalArgumentException(MemberError.ALREADY_EXIST_MEMBER.getMessage());
    }

    return memberRepository.save(member).toDto();
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
        () -> new IllegalArgumentException(MemberError.CANNOT_FIND_MEMBER_BY_ID.getMessage())
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

  /**
   * 멤버가 저장이 되어 있는지 확인
   *
   * @param member 멤버 정보
   * @return 존재한다면 true 반환
   */
  private boolean checkAlreadyMember(Member member) {
    return memberRepository.existsByEmail(member.getEmail());
  }

}
