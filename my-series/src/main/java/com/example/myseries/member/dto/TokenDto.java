package com.example.myseries.member.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TokenDto(
    @JsonProperty("value") String value
) {

  @Override
  @JsonIgnore
  public String toString() {
    return "TokenDto{" +
        "value='" + value + '\'' +
        '}';
  }
}
