package com.example.myseries.novel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class NovelListRequestData {

  private String author;
  private String title;
}
