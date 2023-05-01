package com.example.myseries.novel.service;


import com.example.myseries.novel.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EpisodeService {
  private final EpisodeRepository episodeRepository;

  public void writeEpisode() {

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
