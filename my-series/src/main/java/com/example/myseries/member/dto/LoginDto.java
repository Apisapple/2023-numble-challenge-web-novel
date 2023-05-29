package com.example.myseries.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LoginDto(
  @JsonProperty("user_id") String userId,
  @JsonProperty("password") String password
) {

}
