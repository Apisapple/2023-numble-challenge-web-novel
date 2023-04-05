package com.example.myseries.novel.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NovelCategory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @JoinColumn(name = "NOVEL_ID")
  @ManyToOne(fetch = FetchType.EAGER)
  private Novel novel;

  @JoinColumn(name = "CATEGORY_ID")
  @ManyToOne(fetch = FetchType.EAGER)
  private Category category;

  public NovelCategory(Novel novel, Category category) {
    this.novel = novel;
    this.category = category;
  }
}
