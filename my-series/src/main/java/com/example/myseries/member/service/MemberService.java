package com.example.myseries.member.service;

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
   * @param member member information
   * @return saved member
   */
  @Transactional
  public Member saveMember(Member member) {
    return memberRepository.save(member);
  }


  /**
   * buy point function
   *
   * @param name  member name
   * @param point mount of point
   * @return member information
   */
  @Transactional
  public Member buyPoint(String name, Integer point) {
    Member savecMember = memberRepository.findByName(name).orElseThrow(
        () -> new IllegalArgumentException("CANNOT FOUNT MEMBER")
    );

    savecMember.addPoint(point);

    return savecMember;
  }

  /**
   * use point function
   *
   * @param name  member name
   * @param point mount of point
   * @return member information
   */
  @Transactional
  public Member usePoint(String name, Integer point) {
    Member savecMember = memberRepository.findByName(name).orElseThrow(
        () -> new IllegalArgumentException("CANNOT FOUNT MEMBER")
    );

    savecMember.subPoint(point);

    return savecMember;
  }
}
