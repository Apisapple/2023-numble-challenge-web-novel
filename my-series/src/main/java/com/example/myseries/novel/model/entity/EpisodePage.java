package com.example.myseries.novel.model.entity;

import com.example.myseries.novel.common.entity.BaseTimeEntity;
import com.example.myseries.novel.model.dto.EpisodePageDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Table(name = "episode_page")
public class EpisodePage extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "episode_id")
  private Episode episode;

  @Column(name = "page_number")
  private Integer pageNumber;

  @Column(name = "content", length = 5000)
  private String content;

  @Builder
  public EpisodePage(Episode episode, Integer pageNumber, String content) {
    this.episode = episode;
    this.pageNumber = pageNumber;
    this.content = content;
  }

  public EpisodePageDto toDto() {
    return EpisodePageDto.builder()
        .content(this.content)
        .pageNumber(this.pageNumber)
        .build();
  }
}