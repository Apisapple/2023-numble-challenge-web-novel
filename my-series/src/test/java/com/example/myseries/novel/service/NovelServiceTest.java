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
import com.example.myseries.novel.model.dto.UpdateNovelRequestData;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.repository.NovelRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
//    when(novel.getTitle()).thenReturn("test_title");
    when(novel.getGrade()).thenReturn(0.0f);
    when(novel.toDto()).thenReturn(novelDto);

    when(novelDto.getTitle()).thenReturn("test_title");
    when(novelDto.getAuthorName()).thenReturn(memberDto.getName());

    when(memberDto.getName()).thenReturn("test_user");
  }

  @Test
  @DisplayName(value = "소설 추가 함수")
  void writeNovel() {
    given(memberRepository.findByName(any())).willReturn(Optional.of(author));
    given(novelRepository.existsNovelByTitle(novelDto.getTitle())).willReturn(false);
    given(novelRepository.findNovelByTitleAndAuthor(novelDto.getTitle(), author)).willReturn(
        Optional.ofNullable(novel)
    );

    NovelDto testResult = novelService.writeNovel(novelDto);

    Assertions.assertNotNull(testResult);
  }

  @Test
  @DisplayName(value = "소설 업데이트 테스트")
  void updateNovelInformation() {
    UpdateNovelRequestData request = mock(UpdateNovelRequestData.class);
    Novel savedNovel = Novel.builder()
        .author(author)
        .title("test_title")
        .build();

    when(request.getAuthor()).thenReturn("test_user");
    when(request.getAsIsTitle()).thenReturn("test_title");
    when(request.getToBeTitle()).thenReturn("update_title");

    given(memberRepository.findByName(any())).willReturn(Optional.of(author));
    given(novelRepository.findNovelByTitleAndAuthor(request.getAsIsTitle(), author))
        .willReturn(Optional.ofNullable(savedNovel));

    NovelDto result = novelService.updateNovelInformation(request);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(request.getToBeTitle(), result.getTitle());
  }
}