package smulko.yauheni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import smulko.yauheni.entity.PostAuditLog;

import java.util.List;

@Repository
public interface PostAuditLogRepository extends JpaRepository<PostAuditLog, Long> {
    List<PostAuditLog> findByPostIdOrderByActionTimestampDesc(Long postId);
}
