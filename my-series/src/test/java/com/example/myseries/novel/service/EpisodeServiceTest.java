package com.example.myseries.novel.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.myseries.novel.model.record.EpisodeRequestData;
import com.example.myseries.novel.repository.EpisodeRepository;
import com.example.myseries.novel.repository.NovelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class EpisodeServiceTest {

  @InjectMocks
  private EpisodeService episodeService;

  @Mock
  private NovelRepository novelRepository;

  @Mock
  private EpisodeRepository episodeRepository;

  EpisodeRequestData episodeRequestData = mock(EpisodeRequestData.class);

  @Test
  void writeEpisode() {
    when(episodeRequestData.content()).thenReturn("CONTENT");
  }

  @Test
  void modifyEpisode() {
  }

  @Test
  void removeEpisode() {
  }

  @Test
  void getAllEpisodeList() {
  }

  @Test
  void getEpisodeContent() {
  }
}