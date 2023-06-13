package com.example.myseries.member.dto;

import com.example.myseries.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigInteger;
import java.security.MessageDigest;
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
    String encryptPassword = getSHA512(this.email + this.password);

    return Member.builder()
        .email(this.email)
        .password(encryptPassword)
        .name(this.name)
        .build();
  }

  private String getSHA512(String input) {

    String result = "";
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-512");
      digest.reset();
      digest.update(input.getBytes("utf8"));
      result = String.format("%0128x", new BigInteger(1, digest.digest()));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }
}
