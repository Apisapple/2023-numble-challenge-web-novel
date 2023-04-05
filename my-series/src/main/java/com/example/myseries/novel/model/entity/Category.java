package com.example.myseries.novel.model.entity;

import com.example.myseries.novel.model.dto.CategoryDto;
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

  @Column(name = "VALUE", nullable = false, length = 255)
  private String value;

  @OneToMany(mappedBy = "category")
  private List<NovelCategory> novelCategories;

  @Builder
  public Category(String value) {
    this.value = value;
  }

  public CategoryDto toDto() {
    return CategoryDto.builder()
        .id(this.id)
        .value(this.value)
        .build();
  }
}
