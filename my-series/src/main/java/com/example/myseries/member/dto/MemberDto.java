package com.example.myseries.member.dto;

import com.example.myseries.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
@JsonInclude(Include.NON_EMPTY)
public class MemberDto {

  private Long id;

  private String email;

  private String password;

  private String name;

  private Integer point;

  public Member toEntity() {
    return Member.builder()
        .email(this.email)
        .password(this.password)
        .name(this.name)
        .build();
  }
}
