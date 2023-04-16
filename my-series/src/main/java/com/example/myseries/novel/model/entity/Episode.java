package com.example.myseries.novel.model.entity;

import com.example.myseries.novel.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "episode")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Episode extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name = "content", length = 5000)
  private String content;

  private Long point;

  private Long viewerCnt;

  @ManyToOne
  @JoinColumn(name = "novel_id")
  private Novel novel;

  public void setNovel(Novel novel) {
    this.novel = novel;
  }

  @Builder
  public Episode(String title, String content, Long point, Long viewerCnt) {
    this.title = title;
    this.content = content;
    this.point = 0L;
    this.viewerCnt = 0L;
  }
}