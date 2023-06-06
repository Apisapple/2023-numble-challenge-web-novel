package com.example.myseries.member.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.example.myseries.member.dto.MemberDto;
import com.example.myseries.member.entity.Member;
import com.example.myseries.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @InjectMocks
  private MemberService memberService;

  @Mock
  private MemberRepository memberRepository;


  @Test
  void signup() {
    // TODO : 테스트 코드 추가 작성 필요
    
    MemberDto memberDto = MemberDto.builder()
        .name("test_user")
        .password("1234qwer")
        .point(0)
        .id(1L)
        .build();

    Member member = mock(Member.class);
    given(memberRepository.save(any())).willReturn(member);

    memberService.signup(memberDto);
  }
}