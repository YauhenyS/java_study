package smulko.yauheni.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "post_audit_log")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PostAuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_id", nullable = false)
    private Long postId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, length = 20)
    private String action; // INSERT, UPDATE_TITLE, DELETE

    private String oldTitle;
    private String newTitle;

    @Column(name = "action_timestamp", nullable = false)
    private ZonedDateTime actionTimestamp = ZonedDateTime.now();
}
