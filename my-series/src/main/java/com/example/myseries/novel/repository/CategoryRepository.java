package com.example.myseries.novel.repository;

import com.example.myseries.novel.model.entity.Category;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
  Optional<Category> findCategoryByValue(String value);
}
