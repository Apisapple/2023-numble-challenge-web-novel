package com.example.myseries.novel.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.myseries.novel.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CategoryRepositoryTest {
  @Container
  private static final MySQLContainer<?> MY_SQL_CONTAINER = new MySQLContainer<>("mysql:8.0.24")
      .withExposedPorts(3306)
      .withUsername("root")
      .withPassword("root")
      .withInitScript("initDB.sql");

  @DynamicPropertySource
  public static void properties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername);
    registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);
  }

  @Autowired
  private CategoryRepository categoryRepository;

  @Test
  void findCategoryByValue() {
    Category category = Category.builder()
        .value("FANTASY")
        .build();

    Category category2 = Category.builder()
        .value("먼치킨")
        .build();

    categoryRepository.save(category);
    categoryRepository.save(category2);

    Category savedEng = categoryRepository.findCategoryByValue("FANTASY").orElseThrow(
        () ->  new IllegalArgumentException("FAIL")
    );

    Category savedKor = categoryRepository.findCategoryByValue("먼치킨").orElseThrow(
        () ->  new IllegalArgumentException("FAIL")
    );

    Assertions.assertEquals(savedEng.getValue(), category.getValue());
    Assertions.assertEquals(savedKor.getValue(), category2.getValue());
  }

  @Test
  void deleteCategoryTest() {
    Category category = Category.builder()
        .value("FANTASY")
        .build();

    categoryRepository.save(category);

    Category savedEng = categoryRepository.findCategoryByValue("FANTASY").orElseThrow(
        () ->  new IllegalArgumentException("FAIL")
    );

    categoryRepository.delete(savedEng);

    assertFalse(categoryRepository.findCategoryByValue("FANTASY").isPresent());
  }
}