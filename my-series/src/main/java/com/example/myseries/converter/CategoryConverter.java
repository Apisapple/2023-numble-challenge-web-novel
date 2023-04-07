package com.example.myseries.converter;

import com.example.myseries.novel.model.dto.CategoryDto;
import com.example.myseries.novel.model.entity.Category;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryConverter extends Converter<CategoryDto, Category> {

  public CategoryConverter() {
    super(CategoryConverter::convertToEntity, CategoryConverter::convertToDto);
  }

  private static CategoryDto convertToDto(Category category) {
    return CategoryDto.builder()
        .value(category.getValue())
        .build();
  }

  private static Category convertToEntity(CategoryDto categoryDto) {
    return Category.builder()
        .value(categoryDto.getValue())
        .build();
  }

  public final List<Category> createFromDtos(final Collection<CategoryDto> dtos) {
    return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
  }

  public final List<CategoryDto> createFromEntities(final Collection<Category> entities) {
    return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
  }
}
