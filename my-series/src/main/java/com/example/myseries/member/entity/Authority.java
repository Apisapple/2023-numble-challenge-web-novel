package com.example.myseries.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Authority {

  @Id
  @Column(name = "authority_name", length = 50)
  private String authorityName;

  @Builder
  public Authority(String authorityName) {
    this.authorityName = authorityName;
  }
}
