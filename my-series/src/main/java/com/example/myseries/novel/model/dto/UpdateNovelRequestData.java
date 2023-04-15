package com.example.myseries.novel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateNovelRequestData {

  private String asIsTitle;
  private String toBeTitle;
  private String author;

}
