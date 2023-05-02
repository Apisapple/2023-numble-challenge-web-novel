package com.example.myseries.novel.service;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  void writeEpisode() {
  }

  @Test
  void modifyEpisode() {
  }

  @Test
  void remoeEpisode() {
  }

  @Test
  void getAllEpisodeList() {
  }

  @Test
  void getEpisodeContent() {
  }
}