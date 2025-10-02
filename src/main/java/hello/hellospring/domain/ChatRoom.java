package hello.hellospring.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "LTROOM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ltroom")
    @SequenceGenerator(name = "seq_ltroom", sequenceName = "SEQ_LTROOM", allocationSize = 1)
    @Column(name = "ROOM_ID")
    private Long roomId;

    @Column(name = "ROOM_NAME", length = 100)
    private String roomName;

    @Column(name = "ROOM_TYPE", nullable = false, length = 20)
    private String roomType; // 'DIRECT' or 'GROUP'

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }
}