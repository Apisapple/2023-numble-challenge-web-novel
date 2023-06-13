package com.example.myseries.member.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@JsonFormat(shape = Shape.OBJECT)
public enum MemberError {
  CANNOT_FIND_MEMBER_BY_ID(404, Constant.BASE_CODE + 1, "Cannot find member by id."),
  CANNOT_FIND_MEMBER_BY_NAME(404, Constant.BASE_CODE + 2, "Cannot find member by name."),
  ALREADY_EXIST_MEMBER(400, Constant.BASE_CODE + 3, "Already exist member.");


  private final int status;
  private final int code;
  private final String message;

  private static class Constant {

    private static final int BASE_CODE = 100000;
  }
}
