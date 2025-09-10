package smulko.yauheni.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import smulko.yauheni.entity.PostAuditLog;
import smulko.yauheni.service.PostAuditService;

import java.util.List;

@RestController
@RequestMapping("/private")
public class PostAuditController {

    private final PostAuditService auditService;

    @Autowired
    public PostAuditController(PostAuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping("/audit-info/{postId}")
    public List<PostAuditLog> getAuditInfo(@PathVariable Long postId) {
        return auditService.getAuditLogsByPostId(postId);
    }
}