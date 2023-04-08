package com.example.myseries.novel.service;

import com.example.myseries.novel.repository.NovelRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class NovelServiceTest {

  @InjectMocks
  NovelService novelService;

  @Mock
  NovelRepository novelRepository;

  @Test
  void writeNovel() {
  }

  @Test
  void getAllCategory() {
  }

  @Test
  void makeCategory() {
  }

  @Test
  void deleteCategory() {
  }
}