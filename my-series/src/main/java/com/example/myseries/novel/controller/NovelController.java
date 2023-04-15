package com.example.myseries.novel.controller;

import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.model.dto.UpdateNovelRequestData;
import com.example.myseries.novel.model.dto.WriteNovelRequestData;
import com.example.myseries.novel.service.NovelService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "/api/novel")
public class NovelController {

  private final NovelService novelService;

  @GetMapping("/info")
  public ResponseEntity<NovelDto> getNovelInfo(String title) {
    NovelDto novelDto = novelService.getNovel(title);
    return ResponseEntity.ok(novelDto);
  }

  @GetMapping("/author")
  public ResponseEntity<List<NovelDto>> getNovelInfoList(String name) {
    List<NovelDto> novelDtos = novelService.getNovels(name);
    return ResponseEntity.ok(novelDtos);
  }

  @PostMapping("/write")
  public ResponseEntity<NovelDto> writeNovel(WriteNovelRequestData requestData) {
    NovelDto savedNovel = novelService.writeNovel(requestData);
    return ResponseEntity.ok(savedNovel);
  }

  @PutMapping("/modify")
  public ResponseEntity<NovelDto> updateNovel(UpdateNovelRequestData requestData) {
    NovelDto savedNovel = novelService.updateNovelInformation(requestData);
    return ResponseEntity.ok(savedNovel);
  }

  @DeleteMapping("/delete")
  public ResponseEntity<Boolean> deleteNovel(NovelDto novelDto) {
    Boolean result = novelService.deleteNovel(novelDto);
    return ResponseEntity.ok(result);
  }
}
