package com.example.myseries.novel.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@Builder
@ToString
public class NovelDto {

  private Long id;

  private String novelTitle;

  private String author;

  private Float novelGrade;

  private List<CategoryDto> categoryDtoList;
}
