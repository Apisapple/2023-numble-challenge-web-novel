package com.example.myseries.novel.model.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class NovelDto {

  private Long id;

  private String novelTitle;

  private String author;

  private Float novelGrade;

  private List<CategoryDto> categoryDtoList;
}
