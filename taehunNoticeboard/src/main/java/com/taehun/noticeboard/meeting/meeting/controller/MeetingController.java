package com.taehun.noticeboard.meeting.meeting.controller;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.service.LoginService;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingMemberResponse;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingRequest;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingResponse;
import com.taehun.noticeboard.meeting.meeting.repository.meeting.MeetingRepository;
import com.taehun.noticeboard.meeting.meeting.service.MeetingService;
import jakarta.websocket.server.PathParam;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/meeting"})
public class MeetingController {
    private final MeetingService meetingService;
    private final LoginService loginService;
    private final MeetingRepository meetingRepository;

    @PostMapping(
        consumes = {"application/json", "multipart/form-data"}
    )
    public ResponseEntity createMeeting(@RequestPart MeetingRequest meeting, @RequestPart MultipartFile image, @CookieValue String accessToken) throws IOException {
        this.meetingService.createMeeting(meeting, image, accessToken);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping({"/{meetingId}"})
    public ResponseEntity joinMeeting(@PathVariable long meetingId, @CookieValue String accessToken) {
        User user = this.loginService.findUserByAccessToken(accessToken);
        this.meetingService.joinMeeting(meetingId, user);
        return ResponseEntity.ok().build();
    }

    @PutMapping({"/{meetingId}"})
    public ResponseEntity modifyMeeting(@RequestPart MeetingRequest meeting, @RequestPart(required = false) MultipartFile image, @CookieValue String accessToken, @PathVariable long meetingId) throws IOException {
        this.meetingService.modifyMeeting(meeting, image, accessToken, meetingId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping({"/{meetingId}"})
    public ResponseEntity deleteMeeting(@CookieValue String accessToken, @PathVariable long meetingId) {
        this.meetingService.deleteMeetingById(accessToken, meetingId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping({"/participants/{meetingId}"})
    public ResponseEntity leaveMeeting(@CookieValue String accessToken, @PathVariable long meetingId) {
        this.meetingService.leaveMeeting(accessToken, meetingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity findAllMeeting(Pageable pageable) {
        List<MeetingResponse> meetingList = this.meetingService.findAllMeeting(pageable);
        return ResponseEntity.ok().body(meetingList);
    }

    @GetMapping({"/{meetingId}/user"})
    public ResponseEntity findMeetingByMeetingIdAndUser(@CookieValue String accessToken, @PathVariable long meetingId) {
        MeetingResponse result = this.meetingService.findMeetingByMeetingIdAndUser(accessToken, meetingId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping({"/{meetingId}"})
    public ResponseEntity findMeetingByMeetingId(@PathVariable long meetingId) {
        MeetingResponse result = this.meetingService.findMeetingByMeetingId(meetingId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping({"/count"})
    public ResponseEntity findAllMeetingCount() {
        return ResponseEntity.ok().body(this.meetingRepository.countAllMeeting());
    }

    @GetMapping({"/user"})
    public ResponseEntity findAllMeetingByUserId(Pageable pageable, @CookieValue String accessToken) {
        List<MeetingResponse> meetingList = this.meetingService.findAllMeetingByUserId(pageable, accessToken);
        return ResponseEntity.ok().body(meetingList);
    }

    @GetMapping({"/user/count"})
    public ResponseEntity findAllMeetingByUserIdCount(@CookieValue String accessToken) {
        return ResponseEntity.ok().body(this.meetingService.findAllMeetingByUserIdCount(accessToken));
    }

    @GetMapping({"/title-address"})
    public ResponseEntity findMeetingByTitleOrAddress(Pageable pageable, @PathParam("data") String data) {
        List<MeetingResponse> meetingList = this.meetingService.findMeetingByTitleOrAddress(pageable, data);
        return ResponseEntity.ok().body(meetingList);
    }

    @GetMapping({"/title-address/count"})
    public ResponseEntity findMeetingByTitleOrAddressCount(@PathParam("data") String data) {
        return ResponseEntity.ok().body(this.meetingService.findMeetingByTitleOrAddressCount(data));
    }

    @GetMapping({"/participants/{meetingId}"})
    public ResponseEntity findParticipantsByMeetingId(@PathVariable long meetingId) {
        MeetingMemberResponse result = this.meetingService.findMeetingMemberByMeetingId(meetingId);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping({"/is-participants/{meetingId}"})
    public ResponseEntity IsMeetingParticipant(@PathVariable long meetingId, @CookieValue String accessToken) {
        boolean result = this.meetingService.IsMeetingParticipant(meetingId, accessToken);
        return ResponseEntity.ok().body(result);
    }

    public MeetingController(final MeetingService meetingService, final LoginService loginService, final MeetingRepository meetingRepository) {
        this.meetingService = meetingService;
        this.loginService = loginService;
        this.meetingRepository = meetingRepository;
    }
}