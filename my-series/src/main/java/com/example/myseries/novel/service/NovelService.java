package com.example.myseries.novel.service;

import com.example.myseries.novel.model.dto.CategoryDto;
import com.example.myseries.novel.model.entity.Category;
import com.example.myseries.novel.repository.CategoryRepository;
import com.example.myseries.novel.repository.NovelCategoryRepository;
import com.example.myseries.novel.repository.NovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NovelService {

  private final NovelRepository novelRepository;

  private final CategoryRepository categoryRepository;

  private final NovelCategoryRepository novelCategoryRepository;


  public CategoryDto makeCategory(String value) {
    Category category = categoryRepository.findCategoryByValue(value).orElseGet(
        () -> makeNewCategory(value)
    );
    return category.toDto();
  }

  public boolean deleteCategory(String value) {
    Category category = categoryRepository.findCategoryByValue(value).orElseThrow(
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