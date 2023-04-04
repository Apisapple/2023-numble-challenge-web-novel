package com.example.myseries.member.dto;

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
