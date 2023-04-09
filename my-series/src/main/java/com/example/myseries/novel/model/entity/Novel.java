package com.example.myseries.novel.model.entity;

import com.example.myseries.common.entity.BaseTimeEntity;
import com.example.myseries.member.model.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Entity(name = "NOVEL")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Novel extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "NOVEL_ID", nullable = false)
  private Long id;
  @Column(name = "NOVEL_TITEL", nullable = false)
  private String novelTitle;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "AUTHOR", nullable = false)
  private Member author;

  @Column(name = "NOVEL_GRADE", nullable = false)
  private Float novelGrade;

  @OneToMany(mappedBy = "novel")
  private List<NovelCategory> novelCategories = new ArrayList<>();

  @OneToMany(mappedBy = "novel")
  private List<Episode> episodes;

  @Builder
  public Novel(String novelTitle) {
    this.novelTitle = novelTitle;
    this.novelGrade = 0.0f;
  }

  public void setAuthor(Member author) {
    this.author = author;
  }

  public void addNovelCategory(NovelCategory... categories) {
    novelCategories.addAll(Arrays.asList(categories));
  }

  public void removeNovelCategory(NovelCategory novelCategory) {
    novelCategories.remove(novelCategory);
  }
}
