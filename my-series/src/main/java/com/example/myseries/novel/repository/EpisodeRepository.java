package com.example.myseries.novel.repository;

import com.example.myseries.novel.model.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

}
