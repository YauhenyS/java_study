package smulko.yauheni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smulko.yauheni.entity.PostAuditLog;
import smulko.yauheni.repository.PostAuditLogRepository;

import java.util.List;

@Service
public class PostAuditService {

    private final PostAuditLogRepository auditLogRepository;

    @Autowired
    public PostAuditService(PostAuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public List<PostAuditLog> getAuditLogsByPostId(Long postId) {
        return auditLogRepository.findByPostIdOrderByActionTimestampDesc(postId);
    }
}
