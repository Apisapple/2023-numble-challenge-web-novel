package com.example.myseries.novel.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;

@JsonInclude(Include.NON_EMPTY)
public record EpisodePageDto(String content, Integer pageNumber) {

  @Builder
  public EpisodePageDto {
  }

  @Override
  public String toString() {
    return "EpisodePageDto{" +
        "content='" + content + '\'' +
        ", pageNumber=" + pageNumber +
        '}';
  }
}


