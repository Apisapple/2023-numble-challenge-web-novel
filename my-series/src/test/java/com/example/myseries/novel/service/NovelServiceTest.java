package com.example.myseries.novel.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.myseries.converter.CategoryConverter;
import com.example.myseries.member.model.entity.Member;
import com.example.myseries.member.repository.MemberRepository;
import com.example.myseries.novel.model.dto.CategoryDto;
import com.example.myseries.novel.model.dto.EpisodeDto;
import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.entity.Category;
import com.example.myseries.novel.model.entity.Episode;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.model.entity.NovelCategory;
import com.example.myseries.novel.repository.CategoryRepository;
import com.example.myseries.novel.repository.EpisodeRepository;
import com.example.myseries.novel.repository.NovelRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NovelServiceTest {

  @InjectMocks
  NovelService novelService;

  @Mock
  NovelRepository novelRepository;

  @Mock
  MemberRepository memberRepository;

  @Mock
  CategoryRepository categoryRepository;

  @Mock
  EpisodeRepository episodeRepository;

  @Test
  void writeNovel() {
    Member member = Member.builder()
        .name("TESTER")
        .build();

    Category category = Category.builder()
        .value("CATEGORY")
        .build();

    CategoryDto categoryDto = CategoryDto.builder()
        .value("CATEGORY")
        .build();

    Novel novel = Novel.builder()
        .novelTitle("연습")
        .build();

    NovelCategory novelCategory = new NovelCategory(novel, category);

    novel.addNovelCategory(novelCategory);
    novel.setAuthor(member);

    given(memberRepository.findByName(member.getName())).willReturn(Optional.of(member));
    given(categoryRepository.findCategoryByValue(category.getValue())).willReturn(Optional.of(category));
    given(novelRepository.save(any())).willReturn(novel);

    NovelDto requestData = NovelDto.builder()
        .novelTitle("연습")
        .author("TESTER")
        .categoryDtoList(List.of(categoryDto))
        .build();
    NovelDto novelDto = novelService.writeNovel(requestData);
    System.out.println("NovelData : " + novelDto.toString());
    Assertions.assertNotNull(novelDto);
  }

  @Test
  void writeEpisode() {
    EpisodeDto episodeDto = mock(EpisodeDto.class);
    NovelDto novelDto = mock(NovelDto.class);
    Novel novel = mock(Novel.class);
    Episode episode = mock(Episode.class);
    Member member = mock(Member.class);

    when(episodeDto.getNovelId()).thenReturn(1L);
    when(episodeDto.getTitle()).thenReturn("FIRST EPISODE");
    when(episodeDto.getContent()).thenReturn("TEST CONTENT IS START");
    when(novel.getAuthor()).thenReturn(member);
    when(member.getName()).thenReturn("TESTER");
    when(episode.getTitle()).thenReturn("TITLE");

    given(novelRepository.findNovelById(any())).willReturn(Optional.of(novel));
    given(episodeRepository.save(any())).willReturn(episode);

    NovelDto writeDto = novelService.writeEpisode(episodeDto);

    System.out.println(writeDto.toString());

    Assertions.assertEquals(writeDto.getId(), novelDto.getId());
    Assertions.assertEquals(1, writeDto.getEpisodeDtoList().size());
  }

  @Test
  void getAllCategory() {
    List<Category> categories = List.of(Category.builder().value("FIRST").build(),
        Category.builder().value("SECOND").build());

    boolean flag = false;
    given(categoryRepository.findAll()).willReturn(categories);

    List<CategoryDto> categoryDtos = novelService.getAllCategory();

    Assertions.assertEquals(categories.size(), categoryDtos.size());

    for (int i = 0; i < categoryDtos.size(); i++) {
      if (Objects.equals(categoryDtos.get(i).getValue(), categories.get(i).getValue())) {
        flag = true;
      }
    }

    Assertions.assertTrue(flag);

  }
}