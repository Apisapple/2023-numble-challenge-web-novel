package com.example.myseries.novel.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NOVEL_CATEGORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORY_ID")
  private Long id;

  @Column(name = "CATEGORY", nullable = false, length = 255)
  private String category;

  @OneToMany(mappedBy = "category")
  private List<NovelCategory> novelCategories;
  @Builder
  public Category(String category) {
    this.category = category;
  }

}
