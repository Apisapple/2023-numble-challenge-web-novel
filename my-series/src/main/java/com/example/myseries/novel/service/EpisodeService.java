package com.example.myseries.novel.service;


import com.example.myseries.novel.model.dto.EpisodeDto;
import com.example.myseries.novel.model.entity.Episode;
import com.example.myseries.novel.model.entity.EpisodePage;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.model.record.EpisodeRequestData;
import com.example.myseries.novel.repository.EpisodeRepository;
import com.example.myseries.novel.repository.NovelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class EpisodeService {

  private final EpisodeRepository episodeRepository;
  private final NovelRepository novelRepository;
  private final int contentLength = 500;

  public EpisodeDto writeEpisode(EpisodeRequestData requestData) throws Exception {
    Novel novel = novelRepository.findNovelById(requestData.novelId())
        .orElseThrow(() -> new IllegalArgumentException(""));

    Episode episode = Episode.builder()
        .title(requestData.episodeTitle())
        .build();

    String[] splitContents = requestData.content().split("(?<=\\G.{" + contentLength + "})");
    int indexPage = 0;
    for (String splitContent : splitContents) {
      EpisodePage episodePage = EpisodePage.builder()
          .content(splitContent)
          .episode(episode)
          .pageNumber(indexPage + 1)
          .build();

      episode.addEpisodePage(episodePage);
      indexPage++;
    }
    novel.addEpisode(episode);
    return episodeRepository.save(episode).toDto();
  }

  public void modifyEpisode() {

  }

  public void remoeEpisode() {

  }

  public void getAllEpisodeList() {

  }

  public void getEpisodeContent() {

  }
}
