package com.example.myseries.novel.model.entity;

import com.example.myseries.member.entity.Member;
import com.example.myseries.novel.common.entity.BaseTimeEntity;
import com.example.myseries.novel.model.dto.NovelDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "novel")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Novel extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "title")
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author", nullable = false)
  private Member author;


  @Column(name = "grade")
  private Float grade;

  @Builder
  public Novel(String title, Member author) {
    this.title = title;
    this.author = author;
    this.grade = 0.0f;
  }

  public NovelDto toDto() {
    return NovelDto.builder()
        .title(this.title)
        .author(this.author.toAuthor())
        .grade(this.grade)
        .createdDate(this.getCreatedDate())
        .modifiedDate(this.getModifiedDate())
        .build();
  }

  @Override
  public String toString() {
    return "Novel{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", author=" + author +
        ", grade=" + grade +
        '}';
  }
}