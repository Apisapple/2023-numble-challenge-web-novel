package com.example.myseries.converter;

import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.entity.Category;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.model.entity.NovelCategory;
import java.util.List;

public class NovelConverter extends Converter<NovelDto, Novel> {

  private static final CategoryConverter categoryConverter = new CategoryConverter();
  private static final EpisodeConverter episodeConverter = new EpisodeConverter();
  public NovelConverter() {
    super(NovelConverter::convertToEntity, NovelConverter::convertToDto);
  }

  private static NovelDto convertToDto(Novel novel) {
    List<Category> categories = novel.getNovelCategories().stream()
        .map(NovelCategory::getCategory)
        .toList();

    return NovelDto.builder()
        .id(novel.getId())
        .author(novel.getAuthor().getName())
        .novelTitle(novel.getNovelTitle())
        .novelGrade(novel.getNovelGrade())
        .categoryDtoList(categoryConverter.createFromEntities(categories))
        .episodeDtoList(episodeConverter.createFromEntities(novel.getEpisodes()))
        .build();
  }

  private static Novel convertToEntity(NovelDto novelDto) {
    return Novel.builder()
        .novelTitle(novelDto.getNovelTitle())
        .build();
  }
}
