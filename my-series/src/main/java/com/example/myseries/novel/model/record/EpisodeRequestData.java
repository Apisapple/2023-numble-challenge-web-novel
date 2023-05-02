package com.example.myseries.novel.model.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record EpisodeRequestData(
    @JsonProperty("novel_id") Long novelId,
    @JsonProperty("novel_title") Long novelTitle,
    @JsonProperty("episode_title") String episodeTitle,
    @JsonProperty("content") String content

) {

  @Override
  @JsonIgnore
  public String toString() {
    return "EpisodeRequestData{" +
        "novelId=" + novelId +
        ", novelTitle=" + novelTitle +
        ", episodeTitle='" + episodeTitle + '\'' +
        ", content='" + content + '\'' +
        '}';
  }
}
