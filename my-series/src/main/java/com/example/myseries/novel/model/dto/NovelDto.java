package com.example.myseries.novel.model.dto;

import com.example.myseries.member.dto.MemberDto;
import com.example.myseries.member.entity.Member;
import com.example.myseries.novel.model.entity.Novel;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NovelDto implements Serializable {

  private final String title;
  private final MemberDto author;
  private final Float grade;
  private final LocalDateTime createdDate;
  private final LocalDateTime modifiedDate;

  public Novel toEntity(Member author) {
    return Novel.builder()
        .title(this.title)
        .author(author)
        .build();
  }

}