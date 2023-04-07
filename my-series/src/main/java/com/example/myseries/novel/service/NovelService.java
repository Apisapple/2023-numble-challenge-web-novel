package com.example.myseries.novel.service;

import com.example.myseries.converter.CategoryConverter;
import com.example.myseries.converter.NovelConverter;
import com.example.myseries.novel.model.dto.CategoryDto;
import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.entity.Category;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.model.entity.NovelCategory;
import com.example.myseries.novel.repository.CategoryRepository;
import com.example.myseries.novel.repository.NovelRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelService {

  private final NovelRepository novelRepository;

  private final CategoryRepository categoryRepository;

  private final CategoryConverter categoryConverter = new CategoryConverter();

  private final NovelConverter novelConverter = new NovelConverter();

  public NovelDto writeNovel(NovelDto novelDto) {
    novelRepository.findNovelByNovelTitle(novelDto.getNovelTitle())
        .ifPresent(novel -> {
          throw new IllegalArgumentException("Already exist novel title.");
        });

    Novel novel = novelConverter.convertFromDto(novelDto);

    novelDto.getCategoryDtoList().forEach(categoryDto -> {
      Category category = categoryRepository.findCategoryByValue(categoryDto.getValue())
          .orElse(makeNewCategory(categoryDto.getValue()));

      NovelCategory novelCategory = NovelCategory.builder()
          .novel(novel)
          .category(category)
          .build();

      novel.addNovelCategory(novelCategory);
    });

    Novel savedNovel = novelRepository.save(novel);
    return novelConverter.convertFromEntity(savedNovel);
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
}