package com.taehun.noticeboard.admin.authority.controller;

import com.taehun.noticeboard.admin.authority.dto.ModifyRoleRequest;
import com.taehun.noticeboard.admin.authority.dto.SuspensionRequest;
import com.taehun.noticeboard.admin.authority.service.AuthorityService;
import javax.security.auth.login.AccountException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin/authority"})
public class AuthorityController {
    private final AuthorityService authorityService;

    @GetMapping
    public ResponseEntity findAllAuthorityUser(Pageable pageable, @RequestParam(required = false) String search) {
        return ResponseEntity.ok(this.authorityService.findAllAuthorityUser(pageable, search));
    }

    @GetMapping({"/count"})
    public ResponseEntity findNumberOfAllUsers(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(this.authorityService.findNumberOfAllUsers(search));
    }

    @PatchMapping({"/role"})
    public ResponseEntity modifyAuthorityUserRole(@RequestBody ModifyRoleRequest request) throws AccountException {
        this.authorityService.modifyAuthorityUserRole(request);
        return ResponseEntity.ok().build();
    }

    @PatchMapping({"/suspension"})
    public ResponseEntity modifySuspensionOfUse(@RequestBody SuspensionRequest request) throws AccountException {
        this.authorityService.modifySuspensionOfUse(request);
        return ResponseEntity.ok().build();
    }

    public AuthorityController(final AuthorityService authorityService) {
        this.authorityService = authorityService;
    }
}
