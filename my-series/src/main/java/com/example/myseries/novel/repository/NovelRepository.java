package com.example.myseries.novel.repository;

import com.example.myseries.novel.model.entity.Novel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovelRepository extends JpaRepository<Novel, Long> {

}
