package com.example.myseries.novel.model.dto;

import com.example.myseries.novel.model.entity.Episode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_EMPTY)
public class EpisodeDto {

  private final String title;

  private final String content;

  private final Long point;

  private final Long viewerCnt;

  private final String novelTitle;

  public Episode toEntity() {
    return Episode.builder()
        .title(this.title)
        .viewerCnt(this.viewerCnt)
        .build();
  }

}
