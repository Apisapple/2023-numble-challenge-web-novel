package com.example.myseries.novel.service;

import com.example.myseries.converter.CategoryConverter;
import com.example.myseries.converter.EpisodeConverter;
import com.example.myseries.converter.NovelConverter;
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
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelService {

  private final NovelRepository novelRepository;
  private final EpisodeRepository episodeRepository;
  private final CategoryRepository categoryRepository;
  private final CategoryConverter categoryConverter = new CategoryConverter();
  private final NovelConverter novelConverter = new NovelConverter();
  private final EpisodeConverter episodeConverter = new EpisodeConverter();
  private final MemberRepository memberRepository;

  /**
   * 소설 추구 함수
   *
   * @param novelDto 추가할 소설의 DTO 정보
   * @return 추가된 소설의 DTO 정보
   */
  @Transactional
  public NovelDto writeNovel(NovelDto novelDto) {
    isExistsNovelTitle(novelDto.getNovelTitle());

    Novel novel = novelConverter.convertFromDto(novelDto);
    setCategories(novelDto, novel);
    addAuthor(novelDto, novel);

    Novel savedNovel = novelRepository.save(novel);
    return novelConverter.convertFromEntity(savedNovel);
  }

  /**
   * episode 추가 함수
   *
   * @param episodeDto 에피소드 DTO 객체
   * @return 에피소드가 추가된 NovelDto 객체
   */
  @Transactional
  public NovelDto writeEpisode(EpisodeDto episodeDto) {
    Novel novel = novelRepository.findNovelById(episodeDto.getNovelId()).orElseThrow(
        () -> new IllegalArgumentException("Cannot find novel by novel's id")
    );

    Episode episode = episodeConverter.convertFromDto(episodeDto);
    novel.addEpisode(episode);

    return novelConverter.convertFromEntity(novel);
  }

  public List<CategoryDto> getAllCategory() {
    return categoryConverter.createFromEntities(categoryRepository.findAll());
  }

  /**
   * 카테고리를 추가 함수
   *
   * @param value 추가할 카테고리 정보
   * @return 추가한 카테고리에 대한 DTO 정보
   */
  @Transactional
  public CategoryDto saveCategory(String value) {
    Category category = categoryRepository.findCategoryByValue(value.trim()).orElseGet(
        () -> makeNewCategory(value.trim())
    );
    return categoryConverter.convertFromEntity(category);
  }

  /**
   * 카테고리 삭제 함수
   *
   * @param value 카테고리 값
   * @return 카테고리 삭제 성공 여부
   */
  @Transactional
  public boolean deleteCategory(String value) {
    Category category = categoryRepository.findCategoryByValue(value.trim()).orElseThrow(
        () -> new IllegalArgumentException("Cannot found category.")
    );

    categoryRepository.delete(category);
    return true;
  }

  /**
   * 새로운 카테고리 생성
   *
   * @param value 새로 생성할 카테고리의 값
   * @return Category 새로 생성된 카테고리
   */
  private Category makeNewCategory(String value) {
    Category category = Category.builder()
        .value(value)
        .build();

    return categoryRepository.save(category);
  }

  /**
   * 소설이 이미 존재하는지 검증하는 함수
   *
   * @param novelTitle 소설의 제목
   */
  private void isExistsNovelTitle(String novelTitle) {
    if (novelRepository.existsByNovelTitle(novelTitle)) {
      throw new IllegalArgumentException("Novel title already exists.");
    }
  }

  /**
   * 소설 작가 정보 추가 함수
   *
   * @param novelDto novel DTO
   * @param novel    novel 엔티티
   */
  private void addAuthor(NovelDto novelDto, Novel novel) {
    Member author = memberRepository.findByName(novelDto.getAuthor())
        .orElseThrow(() -> new RuntimeException("Author not found."));
    novel.setAuthor(author);
  }

  /**
   * 엔티티에 카테고리 정보 셋팅 함수
   *
   * @param novelDto novel DTO
   * @param novel    Entity
   */
  private void setCategories(NovelDto novelDto, Novel novel) {
    if (!Objects.isNull(novelDto.getCategoryDtoList())) {
      novelDto.getCategoryDtoList().forEach(categoryDto -> {
        Category category = categoryRepository.findCategoryByValue(categoryDto.getValue())
            .orElse(makeNewCategory(categoryDto.getValue()));

        NovelCategory novelCategory = NovelCategory.builder()
            .novel(novel)
            .category(category)
            .build();

        novel.addNovelCategory(novelCategory);
      });
    }
  }
}