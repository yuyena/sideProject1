package hello.hellospring.repository;

import hello.hellospring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // username으로 사용자 찾기 (로그인용)
    Optional<User> findByUsername(String username);

    // username 중복 체크
    boolean existsByUsername(String username);

    // nickname 중복 체크 (선택사항)
    boolean existsByNickname(String nickname);
    
}