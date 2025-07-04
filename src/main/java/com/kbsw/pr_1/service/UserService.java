package com.kbsw.pr_1.service;

import com.kbsw.pr_1.dto.UserResponseDto;
import com.kbsw.pr_1.dto.UserRequestDto;
import com.kbsw.pr_1.entity.User;
import com.kbsw.pr_1.repository.UserRepository;
import com.kbsw.pr_1.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    public UserResponseDto create(UserRequestDto request) {
        User user = request.toEntity();
        return UserResponseDto.fromEntity(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        return UserResponseDto.fromEntity(user);
    }

    // 회원 가입 (회원 정보 저장 후 JWT 발급)
    public UserResponseDto signUp(UserRequestDto request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = request.toEntity();  // DTO를 Entity로 변환
        user = userRepository.save(user);  // 사용자 저장

        // 회원 가입 후 JWT 토큰 생성
        String jwtToken = jwtTokenProvider.generateToken(user.getEmail());

        return UserResponseDto.fromEntity(user);
    }

    @Transactional(readOnly = true)
    public UserResponseDto findByUsername(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return UserResponseDto.fromEntity(user);
    }

    // 로그인 (아이디, 비밀번호로 인증 후 JWT 생성)
    public String login(UserRequestDto request) {
        // 사용자 정보 조회
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 비밀번호 검증
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 인증된 사용자 정보 설정
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        // SecurityContext에 인증 정보 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 로그s인 후 JWT 토큰 생성
        return jwtTokenProvider.generateToken(request.getEmail());
    }

    public UserResponseDto update(Long id, UserRequestDto request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        user.update(request);
        return UserResponseDto.fromEntity(userRepository.save(user));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}


