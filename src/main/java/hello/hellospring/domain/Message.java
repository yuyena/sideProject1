package hello.hellospring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "LTROOM_MESSAGE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ltroom_msg")
    @SequenceGenerator(name = "seq_ltroom_msg", sequenceName = "SEQ_LTROOM_MSG", allocationSize = 1)
    @Column(name = "MESSAGE_ID")
    private Long messageId;

    @Column(name = "ROOM_ID", nullable = false)
    private Long roomId;

    @Column(name = "SENDER_ID", nullable = false)
    private Long senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOM_ID", insertable = false, updatable = false)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SENDER_ID", insertable = false, updatable = false)
    private User sender;

    @Column(name = "MESSAGE_TYPE", length = 20)
    private String messageType = "TEXT"; // 'TEXT', 'IMAGE', 'FILE'

    @Lob
    @Column(name = "CONTENT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}