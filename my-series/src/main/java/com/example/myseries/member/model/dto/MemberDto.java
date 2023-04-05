package com.example.myseries.member.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class MemberDto {

  private Long id;

  private String name;

  private Integer point;
}
