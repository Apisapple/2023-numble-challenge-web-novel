package com.example.myseries.member.controller;

import com.example.myseries.member.model.dto.MemberDto;
import com.example.myseries.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

  private final MemberService memberService;

  @Operation(summary = "Get Member information by id",
      description = "Get Member information by id")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "NOT FOUND CALENDAR INFORMATION BY ID.")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @GetMapping("/{id}")
  public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
    return ResponseEntity.ok(memberService.getMemberById(id));
  }

  @Operation(summary = "Get Member information by name",
      description = "Get Member information by name")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "NOT FOUND CALENDAR INFORMATION BY ID.")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @GetMapping("/name/{name}")
  public ResponseEntity<MemberDto> getMemberByName(@PathVariable String name) {
    return ResponseEntity.ok(memberService.getMemberByName(name));
  }

  @Operation(summary = "Login",
      description = "Try login by user name")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "NOT FOUND CALENDAR INFORMATION BY ID.")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @PostMapping("/login")
  public ResponseEntity<MemberDto> login(@RequestBody String name) {
    return ResponseEntity.ok(memberService.getMemberByName(name));
  }

  @Operation(summary = "Signup",
      description = "Signup")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "NOT FOUND CALENDAR INFORMATION BY ID.")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @PostMapping("/signup")
  public ResponseEntity<MemberDto> signup(@RequestBody MemberDto memberDto) {
    return ResponseEntity.ok(memberService.saveMember(memberDto.getName()));
  }

  @Operation(summary = "Charging point",
      description = "Charging point")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "NOT FOUND CALENDAR INFORMATION BY ID.")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @PostMapping("/point/charge")
  public ResponseEntity<MemberDto> chargePoint(@RequestBody MemberDto memberDto) {
    return ResponseEntity.ok(memberService.buyPoint(memberDto.getName(), memberDto.getPoint()));
  }

  @Operation(summary = "Using point",
      description = "Using point")
  @ApiResponse(responseCode = "200", description = "OK.")
  @ApiResponse(responseCode = "400", description = "BAD REQUEST.")
  @ApiResponse(responseCode = "404", description = "NOT FOUND CALENDAR INFORMATION BY ID.")
  @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR.")
  @PostMapping("/point/use")
  public ResponseEntity<MemberDto> usePoint(@RequestBody MemberDto memberDto) {
    return ResponseEntity.ok(memberService.buyPoint(memberDto.getName(), memberDto.getPoint()));
  }
}
