package com.kbsw.pr_1.controller;

import com.kbsw.pr_1.dto.UserRequestDto;
import com.kbsw.pr_1.dto.UserResponseDto;
import com.kbsw.pr_1.service.UserService;
import com.kbsw.pr_1.util.CustomUserDetails;
import com.kbsw.pr_1.util.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
@Tag(name = "User", description = "사용자 API")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    // 회원가입
    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "새로운 사용자를 등록합니다.")
    public ResponseEntity<UserResponseDto> signup(@RequestBody UserRequestDto request) {
        return ResponseEntity.status(201).body(userService.signUp(request));
    }

    // 로그인
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자 인증 후 JWT 토큰을 발급합니다.")
    public ResponseEntity<String> login(@RequestBody UserRequestDto request) {
        // 로그인 인증 후 JWT 발급
        String jwtToken = userService.login(request);
        return ResponseEntity.ok(jwtToken);  // JWT 반환
    }

    // 사용자 프로필 조회
    @GetMapping("/profile")
    @Operation(summary = "사용자 프로필 조회", description = "인증된 사용자의 프로필 정보를 조회합니다.")
    public ResponseEntity<UserResponseDto> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        log.info("userDetails:{}",userDetails);
        log.info("userDetails.getUsername() :{}",userDetails.getUsername());
        return ResponseEntity.ok(userService.findByUsername(userDetails.getUsername()));
    }

    // 사용자 업데이트
    @PutMapping
    @Operation(summary = "사용자 정보 수정", description = "인증된 사용자의 정보를 수정합니다.")
    public ResponseEntity<UserResponseDto> update(@AuthenticationPrincipal CustomUserDetails userDetails, @RequestBody UserRequestDto request) {
        return ResponseEntity.ok(userService.update(userDetails.getId(), request));
    }

    // 사용자 삭제
    @DeleteMapping
    @Operation(summary = "사용자 삭제", description = "인증된 사용자의 계정을 삭제합니다.")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal CustomUserDetails userDetails) {
        userService.delete(userDetails.getId());
        return ResponseEntity.noContent().build();
    }


    @PostMapping
    @Operation(summary = "사용자 생성", description = "새로운 사용자를 생성합니다.")
    public ResponseEntity<UserResponseDto> create(@RequestBody UserRequestDto request) {
    log.info("pw: ", request.getPassword());
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(userService.create(request));
    }

    @GetMapping
    @Operation(summary = "모든 사용자 조회", description = "모든 사용자의 정보를 조회합니다.")
    public ResponseEntity<List<UserResponseDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "사용자 조회", description = "ID로 사용자의 정보를 조회합니다.")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }
    @PutMapping("/{id}")
    @Operation(summary = "사용자 정보 수정", description = "ID로 사용자의 정보를 수정합니다.")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @RequestBody UserRequestDto request) {
        return ResponseEntity.ok(userService.update(id, request));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "사용자 삭제", description = "ID로 사용자를 삭제합니다.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }





}
