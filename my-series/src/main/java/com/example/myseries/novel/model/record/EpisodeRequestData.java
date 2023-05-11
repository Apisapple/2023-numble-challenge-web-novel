package com.example.myseries.novel.model.record;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
@JsonInclude(Include.NON_EMPTY)
public record EpisodeRequestData(
    @JsonProperty("novel_id") Long novelId,
    @JsonProperty("novel_title") String novelTitle,
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
