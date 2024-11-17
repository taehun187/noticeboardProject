package com.taehun.noticeboard.account.profile.controller;

import com.taehun.noticeboard.account.profile.dto.ProfileRequest;
import com.taehun.noticeboard.account.profile.service.ProfileService;
import com.taehun.noticeboard.common.response.ResponseMessage;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/profiles"})
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping({"/statistics"})
    public ResponseEntity<ResponseMessage> getStatistics(@CookieValue String accessToken) {
        return ResponseEntity.ok().body(this.profileService.getStatistics(accessToken));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> updateProfile(@RequestBody ProfileRequest profileRequest, @CookieValue String accessToken, HttpServletResponse response) {
        return ResponseEntity.ok().body(this.profileService.updateProfile(profileRequest, accessToken, response));
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getProfileData(@CookieValue String accessToken) {
        return ResponseEntity.ok().body(this.profileService.getProfileFromUser(accessToken));
    }

    public ProfileController(final ProfileService profileService) {
        this.profileService = profileService;
    }
}
