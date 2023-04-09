package com.example.myseries.novel.model.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
public class EpisodeDto {

  private Long id;

  private Long novelId;

  private String title;

  private String content;

  private Float grade;

  private LocalDateTime createdDate;

  private LocalDateTime modifiedDate;
}
