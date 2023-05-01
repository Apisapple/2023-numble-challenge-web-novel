package com.example.myseries.novel.model.entity;

import com.example.myseries.novel.common.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Arrays;
import java.util.List;
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

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "episode")
  private List<EpisodePage> pages = new java.util.ArrayList<>();

  private Integer price;
  private Long totalGrade;
  private Long gradeCnt;
  private Long viewerCnt;

  @ManyToOne
  @JoinColumn(name = "novel_id")
  private Novel novel;

  public void setNovel(Novel novel) {
    this.novel = novel;
  }

  @Builder
  public Episode(String title, Integer price, Long viewerCnt) {
    this.title = title;
    this.price = price;
    this.totalGrade = 0L;
    this.gradeCnt = 0L;
    this.viewerCnt = 0L;
  }

  /**
   * 에피소드 목록 추가
   * @param episodePages 에피소드 페이지 정보들
   */
  public void addEpisodePage(EpisodePage... episodePages) {
    pages.addAll(Arrays.asList(episodePages));
  }

}