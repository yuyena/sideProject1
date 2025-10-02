package hello.hellospring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "LTROOM_MEMBER")
@IdClass(RoomMemberId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomMember {

    @Id
    @Column(name = "ROOM_ID")
    private Long roomId;

    @Id
    @Column(name = "USER_ID")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", insertable = false, updatable = false)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", insertable = false, updatable = false)
    private User user;

    @Column(name = "LAST_READ_MSG_ID")
    private Long lastReadMsgId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "JOINED_AT", updatable = false)
    private Date joinedAt;

    @PrePersist
    protected void onCreate() {
        this.joinedAt = new Date();
    }
}