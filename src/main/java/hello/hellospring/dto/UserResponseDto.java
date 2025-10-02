package hello.hellospring.dto;


import hello.hellospring.domain.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

    private Long userId;
    private String username;
    private String nickname;
    private String statusMsg;
    private String profileImg;
    private Date createdAt;

    // Entity -> DTO 변환
    public static UserResponseDto from(User user) {
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .statusMsg(user.getStatusMsg())
                .profileImg(user.getProfileImg())
                .createdAt(user.getCreatedAt())
                .build();
    }
}