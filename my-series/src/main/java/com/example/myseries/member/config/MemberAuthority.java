package com.example.myseries.member.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MemberAuthority {

  ADMIN("ADMIN"),
  NORMAL("NORMAL_MEMBER"),
  WRITER("WRITER");

  private String role;
}
