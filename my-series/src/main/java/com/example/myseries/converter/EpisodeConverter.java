package com.example.myseries.converter;

import com.example.myseries.novel.model.dto.EpisodeDto;
import com.example.myseries.novel.model.entity.Episode;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EpisodeConverter extends Converter<EpisodeDto, Episode> {


  public EpisodeConverter() {
    super(EpisodeConverter::convertToEntity, EpisodeConverter::convertToDto);
  }

  private static EpisodeDto convertToDto(Episode episode) {
    return EpisodeDto.builder()
        .id(episode.getId())
        .novelId(episode.getNovel().getId())
        .title(episode.getTitle())
        .content(episode.getContent())
        .createdDate(episode.getCreatedDate())
        .modifiedDate(episode.getModifiedDate())
        .grade(episode.getGrade())
        .build();
  }

  private static Episode convertToEntity(EpisodeDto episodeDto) {
    return null;
  }

  public final List<Episode> createFromDtos(final Collection<EpisodeDto> dtos) {
    return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
  }

  public final List<EpisodeDto> createFromEntities(final Collection<Episode> entities) {
    return entities.stream().map(this::convertFromEntity).collect(Collectors.toList());
  }
}
