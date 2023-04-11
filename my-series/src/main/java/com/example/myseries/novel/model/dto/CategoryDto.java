package com.example.myseries.novel.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
public class CategoryDto {

  private Long id;

  private String value;

  public String getValue() {
    return value.trim();
  }

  public void setValue(String value) {
    this.value = value;
  }
}
