package com.example.myseries.novel.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum EpisodeError implements EnumInterface {
  CANNOT_FIND_EPISODE_BY_ID(404, Constant.BASE_CODE + 1, "Cannot find episode by id");


  private final int status;
  private final int code;
  private final String message;

  @Override
  public int getKey() {
    return 0;
  }

  @Override
  public String getValue() {
    return null;
  }

  private static class Constant {

    private static final int BASE_CODE = 300000;
  }
}
