package com.example.myseries.novel.model.dto;

import com.example.myseries.member.entity.Member;
import com.example.myseries.novel.model.entity.Novel;
import java.io.Serializable;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NovelDto implements Serializable {

  private final String title;
  private final String authorName;
  private final Float grade;
  private final String createdDate;
  private final String modifiedDate;

  public Novel toEntity(Member author) {
    return Novel.builder()
        .title(this.title)
        .author(author)
        .build();
  }

}