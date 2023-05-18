package com.example.myseries.member.config;

import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TokenProvider implements InitializingBean {

  private final String secretKey;
  private final Integer validitySeconds;
  private Key key;

  public TokenProvider(@Value("${jwt.secret-key}") String secretKey,
      @Value("${jwt.validity-seconds}") Integer validitySeconds) {
    this.secretKey = secretKey;
    this.validitySeconds = validitySeconds * 1000;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    byte[] secretKeyBytes = Base64.getDecoder().decode(secretKey);
    this.key = Keys.hmacShaKeyFor(secretKeyBytes);
  }
}
