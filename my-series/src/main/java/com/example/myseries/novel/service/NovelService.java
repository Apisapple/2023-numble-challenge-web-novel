package com.example.myseries.novel.service;

import com.example.myseries.converter.CategoryConverter;
import com.example.myseries.converter.NovelConverter;
import com.example.myseries.member.model.entity.Member;
import com.example.myseries.member.repository.MemberRepository;
import com.example.myseries.novel.model.dto.CategoryDto;
import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.entity.Category;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.model.entity.NovelCategory;
import com.example.myseries.novel.repository.CategoryRepository;
import com.example.myseries.novel.repository.NovelRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelService {

  private final NovelRepository novelRepository;

  private final CategoryRepository categoryRepository;

  private final CategoryConverter categoryConverter = new CategoryConverter();

  private final NovelConverter novelConverter = new NovelConverter();
  private final MemberRepository memberRepository;

  public NovelDto writeNovel(NovelDto novelDto) {
    validateNovelTitle(novelDto.getNovelTitle());

    Novel novel = novelConverter.convertFromDto(novelDto);
    setCategories(novelDto, novel);
    addAuthor(novelDto, novel);

    Novel savedNovel = novelRepository.save(novel);
    return novelConverter.convertFromEntity(savedNovel);
  }

  public void updateNovel(NovelDto novelDto) {

  }

  public void deleteNovel(NovelDto novelDto) {
    /** TODO
     *
     * 1. 구매 목록이 있을 경우 삭제 X
     * 2. 에피소드가 추가 되어 있을 경우, 삭제 X
     * */
  }

  public void WriteEpisode() {

  }

  public List<CategoryDto> getAllCategory() {
    return categoryConverter.createFromEntities(categoryRepository.findAll());
  }

  public CategoryDto makeCategory(String value) {
    Category category = categoryRepository.findCategoryByValue(value.trim()).orElseGet(
        () -> makeNewCategory(value.trim())
    );
    return categoryConverter.convertFromEntity(category);
  }

  public boolean deleteCategory(String value) {
    Category category = categoryRepository.findCategoryByValue(value.trim()).orElseThrow(
        () -> new IllegalArgumentException("Cannot found category.")
    );

    categoryRepository.delete(category);
    return true;
  }

  private Category makeNewCategory(String value) {
    Category category = Category.builder()
        .value(value)
        .build();

    return categoryRepository.save(category);
  }

  private void validateNovelTitle(String novelTitle) {
    if (novelRepository.existsByNovelTitle(novelTitle)) {
      throw new IllegalArgumentException("Novel title already exists.");
    }
  }

  private void addAuthor(NovelDto novelDto, Novel novel) {
    Member author = memberRepository.findByName(novelDto.getAuthor())
        .orElseThrow(() -> new RuntimeException("Author not found."));
    novel.setAuthor(author);
  }
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