package com.example.myseries.novel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;


import com.example.myseries.member.dto.MemberDto;
import com.example.myseries.member.entity.Member;
import com.example.myseries.member.repository.MemberRepository;
import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.repository.NovelRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NovelServiceTest {

  @InjectMocks
  private NovelService novelService;

  @Mock
  private MemberRepository memberRepository;

  @Mock
  private NovelRepository novelRepository;

  Novel novel = mock(Novel.class);
  Member author = mock(Member.class);

  NovelDto novelDto = mock(NovelDto.class);
  MemberDto memberDto = mock(MemberDto.class);

  @BeforeEach
  void initTest() {
    when(author.getId()).thenReturn(1L);
    when(author.getName()).thenReturn("test_user");
    when(author.getPoint()).thenReturn(0);

    when(novel.getId()).thenReturn(1L);
    when(novel.getAuthor()).thenReturn(author);
    when(novel.getTitle()).thenReturn("test_title");
    when(novel.getGrade()).thenReturn(0.0f);
    when(novel.toDto()).thenReturn(novelDto);

    when(novelDto.getTitle()).thenReturn("test_title");
    when(novelDto.getAuthor()).thenReturn(memberDto);

    when(memberDto.getName()).thenReturn("test_user");
  }

  @Test
  void writeNovel() {
    given(memberRepository.findByName(any())).willReturn(Optional.of(author));
    given(novelRepository.existsNovelByTitle(novelDto.getTitle())).willReturn(false);
    given(novelRepository.findNovelByTitleAndAuthor(novelDto.getTitle(), author)).willReturn(
        Optional.ofNullable(novel)
    );

    NovelDto testResult = novelService.writeNovel(novelDto);

    Assertions.assertNotNull(testResult);
  }
}