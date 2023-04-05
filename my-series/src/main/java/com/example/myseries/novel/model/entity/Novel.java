package com.example.myseries.novel.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "NOVEL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Novel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "NOVEL_ID", nullable = false)
  private Long id;
  @Column(name = "NOVEL_TITEL", nullable = false)
  private String novelTitle;
  @Column(name = "AUTHOR", nullable = false)
  private String author;
  @Column(name = "NOVEL_GRADE", nullable = false)
  private Float novelGrade;

  @OneToMany(mappedBy = "novel")
  private List<NovelCategory> novelCategories;

  @Builder
  public Novel(String novelTitle, String author) {
    this.novelTitle = novelTitle;
    this.author = author;
    this.novelGrade = 0.0f;
  }
}