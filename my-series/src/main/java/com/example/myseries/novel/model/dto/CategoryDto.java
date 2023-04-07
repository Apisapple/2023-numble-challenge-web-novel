package com.example.myseries.novel.model.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class CategoryDto {
  private String value;

  public String getValue() {
    return value.trim();
  }

  public void setValue(String value) {
    this.value = value;
  }
}
