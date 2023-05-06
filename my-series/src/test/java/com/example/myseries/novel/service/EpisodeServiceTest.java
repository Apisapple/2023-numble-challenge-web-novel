package com.example.myseries.novel.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.example.myseries.novel.model.dto.EpisodeDto;
import com.example.myseries.novel.model.entity.Episode;
import com.example.myseries.novel.model.entity.Novel;
import com.example.myseries.novel.model.record.EpisodeRequestData;
import com.example.myseries.novel.repository.EpisodeRepository;
import com.example.myseries.novel.repository.NovelRepository;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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

  private final String CONTENT = "content content content content content content content content content content content content content";
  Novel novel = mock(Novel.class);
  Episode episode = mock(Episode.class);
  EpisodeDto episodeDto = mock(EpisodeDto.class);
  @Test
  @DisplayName("에피소드 추가 테스트")
  void writeEpisode() throws Exception {

    //given
    when(novel.getId()).thenReturn(1L);
    when(episode.toDto()).thenReturn(episodeDto);
    when(episodeDto.getTitle()).thenReturn("EPISODE_TITLE");
    EpisodeRequestData episodeRequestData = EpisodeRequestData.builder()
        .episodeTitle("EPISODE_TITLE")
        .novelId(1L)
        .novelTitle("NOVEL_TITLE")
        .content(CONTENT)
        .build();

    //when
    given(novelRepository.findNovelById(any())).willReturn(Optional.of(novel));
    given(episodeRepository.save(any())).willReturn(episode);

    EpisodeDto episodeDto = episodeService.writeEpisode(episodeRequestData);

    //then
    Assertions.assertEquals(episodeDto.getTitle(), episodeRequestData.episodeTitle());
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