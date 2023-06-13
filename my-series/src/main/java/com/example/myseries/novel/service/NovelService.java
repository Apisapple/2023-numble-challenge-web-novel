package com.example.myseries.novel.service;

import com.example.myseries.member.dto.MemberDto;
import com.example.myseries.member.entity.Member;
import com.example.myseries.member.repository.MemberRepository;
import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.dto.NovelListRequestData;
import com.example.myseries.novel.model.dto.UpdateNovelRequestData;
import com.example.myseries.novel.model.dto.WriteNovelRequestData;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.repository.NovelRepository;
import java.util.List;
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
   * @param requestData 소설 정보
   * @return 저장된 소설 정보
   */
  @Transactional
  public NovelDto writeNovel(WriteNovelRequestData requestData) {

    Member author = getAuthor(requestData.getAuthor());

    if (novelRepository.existsNovelByTitle(requestData.getTitle())) {
      throw new IllegalStateException("이미 존재하는 소설 제목 입니다.");
    }

    Novel novel = novelRepository.findNovelByTitleAndAuthor(requestData.getTitle(), author)
        .orElseGet(() -> saveNewNovel(NovelDto.builder()
            .title(requestData.getTitle())
            .authorName(requestData.getAuthor())
            .build(), author));

    return novel.toDto();
  }

  /**
   * 소설 하나의 정보 가져오기
   *
   * @param title 찾을 소설 제목
   * @return 조회한 소설 정보
   */
  public NovelDto getNovel(String title) {
    Novel novel = novelRepository.findNovelByTitle(title)
        .orElseThrow(() -> new IllegalArgumentException("소설의 정보를 찾을 수 없습니다."));
    return novel.toDto();
  }

  /**
   * 한 작가의 소설 목록 조회
   *
   * @param name 작가의 이름
   * @return 소설 리스트
   */
  public List<NovelDto> getNovels(String name) {
    Member author = getAuthor(name);
    List<Novel> novels = author.getNovels();
    return novels.stream().map(Novel::toDto).toList();
  }


  /**
   * 소설 정보 수정 함수
   *
   * @param requestData 소설 정보 변경 요청 데이터
   * @return 변경된 소설 정보
   */
  @Transactional
  public NovelDto updateNovelInformation(UpdateNovelRequestData requestData) {
    Member author = getAuthor(requestData.getAuthor());

    Novel novel = novelRepository.findNovelByTitleAndAuthor(requestData.getAsIsTitle(), author)
        .orElseThrow(() -> new IllegalArgumentException("소설 정보를 찾을 수 없습니다."));

    novel.updateTitle(requestData.getToBeTitle());

    return novel.toDto();
  }

  /**
   * 소설 정보 삭제 함수
   *
   * @param novelDto 삭제할 소설의 DTO
   * @return 삭제 성공 여부
   */
  @Transactional
  public Boolean deleteNovel(NovelDto novelDto) {
    Member author = getAuthor(novelDto.getAuthorName());
    Novel novel = novelRepository.findNovelByTitleAndAuthor(novelDto.getTitle(), author)
        .orElseThrow(() -> new IllegalArgumentException("소설 정보를 찾을 수 없습니다."));

    novelRepository.delete(novel);

    return true;
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

  /**
   * 작가 정보 조회 함수
   *
   * @param name 계정 이름
   * @return 계정 정보
   */
  private Member getAuthor(String name) {
    return memberRepository.findByName(name)
        .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 멤버 입니다."));
  }
}

