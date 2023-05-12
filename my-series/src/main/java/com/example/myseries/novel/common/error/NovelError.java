package com.example.myseries.novel.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@JsonFormat(shape = Shape.OBJECT)
public enum NovelError implements EnumInterface {
  CANNOT_FOUND_NOVEL_BY_ID(404, Constant.BASE_CODE + 1, "Cannot found novel by novel id."),
  CANNOT_FOUND_NOVEL_BY_TITLE(404, Constant.BASE_CODE + 2, "Cannot found novel by novel title.");

  private final int status;
  private final int code;
  private final String message;

  @Override
  public int getKey() {
    return this.code;
  }

  @Override
  public String getValue() {
    return this.message;
  }

  private static class Constant {

    private static final int BASE_CODE = 200000;
  }
}
