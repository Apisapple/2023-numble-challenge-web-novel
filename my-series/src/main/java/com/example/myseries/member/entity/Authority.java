package com.example.myseries.member.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.Builder;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Authority {

  @Id
  @Column(length = 50)
  private String authorityName;

  @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL)
  private Set<MemberAuthority> members;

  @Builder
  public Authority(String authorityName) {
    this.authorityName = authorityName;
  }
}
