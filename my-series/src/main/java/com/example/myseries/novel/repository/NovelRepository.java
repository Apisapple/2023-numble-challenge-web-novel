package com.example.myseries.novel.repository;

import com.example.myseries.member.entity.Member;
import com.example.myseries.novel.model.entity.Novel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<Novel, Long> {

  Optional<Novel> findNovelByTitle(String title);

  Optional<Novel> findNovelByTitleAndAuthor(String title, Member author);

  Optional<Novel> findNovelById(Long id);

  boolean existsNovelByTitle(String title);
}