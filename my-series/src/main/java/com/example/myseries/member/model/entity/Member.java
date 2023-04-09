package com.example.myseries.member.model.entity;

import com.example.myseries.common.entity.BaseTimeEntity;
import com.example.myseries.member.model.dto.MemberDto;
import com.example.myseries.novel.model.entity.Novel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private Integer point;

  @OneToMany(mappedBy = "id")
  private List<Novel> novels;

  public void writeNovel(Novel novel) {
    this.novels.add(novel);
  }

  @Builder
  public Member(String name) {
    this.name = name;
    this.point = 0;
  }

  public void addPoint(Integer point) {
    this.point += point;
  }

  public void subPoint(Integer point) {
    if(this.point - point >= 0) {
      this.point = this.point - point;
    }
  }

  public MemberDto toDto() {
    return MemberDto.builder()
        .id(this.id)
        .name(this.name)
        .point(this.point)
        .build();
  }
}
