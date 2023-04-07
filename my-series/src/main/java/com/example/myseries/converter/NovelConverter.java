package com.example.myseries.converter;

import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.entity.Category;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.model.entity.NovelCategory;
import java.util.List;

public class NovelConverter extends Converter<NovelDto, Novel> {

  private static final CategoryConverter categoryConverter = new CategoryConverter();

  public NovelConverter() {
    super(NovelConverter::convertToEntity, NovelConverter::convertToDto);
  }

  private static NovelDto convertToDto(Novel novel) {
    List<Category> categories = novel.getNovelCategories().stream()
        .map(NovelCategory::getCategory)
        .toList();

    return NovelDto.builder()
        .id(novel.getId())
        .author(novel.getAuthor())
        .novelTitle(novel.getNovelTitle())
        .novelGrade(novel.getNovelGrade())
        .categoryDtoList(categoryConverter.createFromEntities(categories))
        .build();
  }

  private static Novel convertToEntity(NovelDto novelDto) {
    return Novel.builder()
        .author(novelDto.getAuthor())
        .novelTitle(novelDto.getNovelTitle())
        .build();
  }
}
