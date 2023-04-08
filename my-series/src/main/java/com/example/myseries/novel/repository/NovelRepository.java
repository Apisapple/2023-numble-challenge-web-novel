package com.example.myseries.novel.repository;

import com.example.myseries.novel.model.entity.Novel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<Novel, Long> {

  Optional<Novel> findNovelByNovelTitle(String title);

  boolean existsByNovelTitle(String title);
}
