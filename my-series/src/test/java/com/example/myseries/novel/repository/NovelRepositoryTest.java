package com.example.myseries.novel.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.example.myseries.member.entity.Member;
import com.example.myseries.novel.model.entity.Novel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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
class NovelRepositoryTest {

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
  private NovelRepository novelRepository;

  @Test
  void findNovelByTitle() {
    Member author = Member.builder()
        .name("test_user")
        .build();

    Novel novel = Novel.builder()
        .author(author)
        .title("test_title")
        .build();

    author.writeNovel(novel);
    novelRepository.save(novel);

    Novel savedNovel = novelRepository.findNovelByTitle("test_title").orElseThrow(
        () -> new IllegalArgumentException("Cannot found")
    );

    assertNotNull(savedNovel);
    Assertions.assertEquals(novel.getTitle(), savedNovel.getTitle());
    Assertions.assertEquals("test_user", novel.getAuthor().getName());
  }

  @Test
  void existsNovelByTitle() {
  }
}