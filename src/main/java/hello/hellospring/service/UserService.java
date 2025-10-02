package hello.hellospring.service;


import hello.hellospring.domain.User;
import hello.hellospring.dto.UserRegisterDto;
import hello.hellospring.dto.UserResponseDto;
import hello.hellospring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    // TODO: 나중에 PasswordEncoder 추가 예정

    /**
     * 회원가입
     */
    public UserResponseDto register(UserRegisterDto dto) {
        // 1. 아이디 중복 체크
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }

        // 2. 닉네임 중복 체크 (선택사항)
        if (userRepository.existsByNickname(dto.getNickname())) {
            throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
        }

        // 3. 비밀번호 확인
        if (!dto.getPassword().equals(dto.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 4. 비밀번호 암호화 (TODO: BCryptPasswordEncoder 사용)
        // String encodedPassword = passwordEncoder.encode(dto.getPassword());
        String encodedPassword = dto.getPassword(); // 임시로 평문 저장

        // 5. User 엔티티 생성
        User user = User.builder()
                .username(dto.getUsername())
                .password(encodedPassword)
                .nickname(dto.getNickname())
                .statusMsg(dto.getStatusMsg())
                .build();

        // 6. 저장
        User savedUser = userRepository.save(user);

        // 7. DTO로 변환하여 반환
        return UserResponseDto.from(savedUser);
    }

    /**
     * 아이디 중복 체크
     */
    @Transactional(readOnly = true)
    public boolean checkUsernameDuplicate(String username) {
        return userRepository.existsByUsername(username);
    }

    /**
     * 닉네임 중복 체크
     */
    @Transactional(readOnly = true)
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}