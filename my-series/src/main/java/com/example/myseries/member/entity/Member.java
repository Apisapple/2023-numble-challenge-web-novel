package com.example.myseries.member.entity;

import com.example.myseries.member.dto.MemberDto;
import jakarta.persistence.CascadeType;
import com.example.myseries.novel.model.entity.Novel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String password;

  @OneToMany(mappedBy = "id")
  private List<Novel> novels = new ArrayList<>();

  private String name;

  private Integer point;

  @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
  private Set<MemberAuthority> memberAuthorities = new HashSet<>();

  @Builder
  public Member(String email, String password, String name) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.point = 0;
  }

  public void addPoint(Integer point) {
    this.point += point;
  }

  public void subPoint(Integer point) {
    if (this.point - point >= 0) {
      this.point = this.point - point;
    }
  }

  public void writeNovel(Novel novel) {
    this.novels.add(novel);
  }

  public MemberDto toDto() {
    return MemberDto.builder()
        .id(this.id)
        .email(this.email)
        .password(this.password)
        .name(this.name)
        .point(this.point)
        .build();
  }


  public void giveAuthority(MemberAuthority memberAuthority) {
    this.memberAuthorities.add(memberAuthority);
  }
}
