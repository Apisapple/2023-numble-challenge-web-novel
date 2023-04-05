package com.example.myseries.member.controller;

import com.example.myseries.member.model.dto.MemberDto;
import com.example.myseries.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService;

  @GetMapping("/api/member/{id}")
  public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
    return ResponseEntity.ok(memberService.getMemberById(id));
  }

  @PostMapping("/api/member/login")
  public ResponseEntity<MemberDto> login(@RequestBody String name) {
    return ResponseEntity.ok(memberService.getMemberByName(name));
  }

  @PostMapping("/api/point/charge")
  public ResponseEntity<MemberDto> chargePoint(@RequestBody MemberDto memberDto) {
    return ResponseEntity.ok(memberService.buyPoint(memberDto.getName(), memberDto.getPoint()));
  }

  @PostMapping("/api/point/use")
  public ResponseEntity<MemberDto> usePoint(@RequestBody MemberDto memberDto) {
    return ResponseEntity.ok(memberService.buyPoint(memberDto.getName(), memberDto.getPoint()));
  }
}
