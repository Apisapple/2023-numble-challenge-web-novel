package com.example.myseries.member.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Authority {

  @Id
  @Column(length = 50)
  private AuthorityData authority;

  @OneToMany(mappedBy = "authority", cascade = CascadeType.ALL)
  private Set<MemberAuthority> members;

  @Builder
  public Authority(AuthorityData authority) {
    this.authority = authority;
  }

  @AllArgsConstructor
  @Getter
  public enum AuthorityData {

    ADMIN("ADMIN"),
    NORMAL("NORMAL_MEMBER"),
    WRITER("WRITER");

    private String role;
  }

}
