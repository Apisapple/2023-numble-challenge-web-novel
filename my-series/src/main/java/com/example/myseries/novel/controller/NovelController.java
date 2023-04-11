package com.example.myseries.novel.controller;

import com.example.myseries.novel.model.dto.CategoryDto;
import com.example.myseries.novel.model.dto.EpisodeDto;
import com.example.myseries.novel.model.dto.NovelDto;
import com.example.myseries.novel.service.NovelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/novel")
@RequiredArgsConstructor
public class NovelController {

  private final NovelService novelService;

  @Operation(summary = "Write novel cover",
      description = "Write cover of novel")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "EXCEPTION")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @PostMapping("/cover")
  public NovelDto writeNovel(@RequestBody NovelDto novelDto) {
    return novelService.writeNovel(novelDto);
  }



  @Operation(summary = "Write novel's episode",
      description = "소설에 에피소드를 추가")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "EXCEPTION")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @PostMapping("/episode")
  public NovelDto writeEpisode(@RequestBody EpisodeDto episodeDto) {
    return novelService.writeEpisode(episodeDto);
  }


  @Operation(summary = "Get all category",
      description = "Get all category")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "EXCEPTION")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @GetMapping("/category")
  public List<CategoryDto> getAllCategory() {
    return novelService.getAllCategory();
  }

  @Operation(summary = "Save category",
      description = "Save category")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "EXCEPTION")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @PostMapping("/category")
  public CategoryDto saveCategory(@RequestBody String value) {
    return novelService.saveCategory(value);
  }

  @Operation(summary = "Delete category",
      description = "Delete category")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "EXCEPTION")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @DeleteMapping("/category")
  public boolean deleteCategory(@RequestBody String value) {
    return novelService.deleteCategory(value);
  }
}
