package com.example.myseries.novel.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.example.myseries.converter.CategoryConverter;
import com.example.myseries.member.model.entity.Member;
import com.example.myseries.member.repository.MemberRepository;
import com.example.myseries.novel.model.dto.CategoryDto;
import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.entity.Category;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.model.entity.NovelCategory;
import com.example.myseries.novel.repository.CategoryRepository;
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