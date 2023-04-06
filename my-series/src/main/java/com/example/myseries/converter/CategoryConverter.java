package com.example.myseries.converter;

import com.example.myseries.novel.model.dto.CategoryDto;
import com.example.myseries.novel.model.entity.Category;

public class CategoryConverter extends Converter<CategoryDto, Category> {

  public CategoryConverter() {
    super(CategoryConverter::convertToEntity, CategoryConverter::convertToDto);
  }

  private static CategoryDto convertToDto(Category category) {
    return CategoryDto.builder()
        .id(category.getId())
        .value(category.getValue())
        .build();
  }

  private static Category convertToEntity(CategoryDto categoryDto) {
    return Category.builder()
        .value(categoryDto.getValue())
        .build();
  }
}
