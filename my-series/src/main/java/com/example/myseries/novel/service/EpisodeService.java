package com.example.myseries.novel.service;


import com.example.myseries.novel.model.entity.Episode;
import com.example.myseries.novel.model.entity.EpisodePage;
import com.example.myseries.novel.model.record.EpisodeRequestData;
import com.example.myseries.novel.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EpisodeService {

  private final EpisodeRepository episodeRepository;
  private final int contentLength = 500;

  public void writeEpisode(EpisodeRequestData requestData) {
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
    episodeRepository.save(episode);
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
