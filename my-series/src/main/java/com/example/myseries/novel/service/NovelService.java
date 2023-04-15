package com.example.myseries.novel.service;

import com.example.myseries.member.entity.Member;
import com.example.myseries.member.repository.MemberRepository;
import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.repository.NovelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NovelService {

  private final NovelRepository novelRepository;

  private final MemberRepository memberRepository;

  /**
   * 소설 정보 추가 (저장이 되어 있는 목록이 있을 경우, 기존 정보 반환)
   *
   * @param novelDto 소설 정보
   * @return 저장된 소설 정보
   */
  @Transactional
  public NovelDto writeNovel(NovelDto novelDto) {

    Member author = memberRepository.findByName(novelDto.getAuthor().getName())
        .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 사용자 입니다."));

    if (novelRepository.existsNovelByTitle(novelDto.getTitle())) {
      throw new IllegalStateException("이미 존재하는 소설 제목 입니다.");
    }

    Novel novel = novelRepository.findNovelByTitleAndAuthor(novelDto.getTitle(), author)
        .orElseGet(() -> saveNewNovel(novelDto, author));

    return novel.toDto();
  }

  /**
   * 신작 소설 저장
   *
   * @param novelDto 소설 정보
   * @param author   작가
   * @return 새롭게 저장된 소설 엔티티 반환
   */
  private Novel saveNewNovel(NovelDto novelDto, Member author) {
    Novel novel = novelDto.toEntity(author);
    return novelRepository.save(novel);
  }

}

