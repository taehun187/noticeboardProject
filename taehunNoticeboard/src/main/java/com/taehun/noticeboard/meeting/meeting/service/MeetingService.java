package com.taehun.noticeboard.meeting.meeting.service;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.service.LoginService;
import com.taehun.noticeboard.common.response.message.MeetingMessage;
import com.taehun.noticeboard.meeting.chat.service.ChatService;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.meeting.meeting.domain.MeetingParticipant;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingMemberResponse;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingRequest;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingResponse;
import com.taehun.noticeboard.meeting.meeting.dto.ParticipantResponse;
import com.taehun.noticeboard.meeting.meeting.exception.MeetingException;
import com.taehun.noticeboard.meeting.meeting.repository.meeting.MeetingRepository;
import com.taehun.noticeboard.meeting.meeting.repository.participant.MeetingParticipantRepository;
import com.taehun.noticeboard.s3.service.S3Service;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class MeetingService {
    private final MeetingRepository meetingRepository;
    private final S3Service s3Service;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoginService loginService;
    private final MeetingParticipantRepository meetingParticipantRepository;
    private final ChatService chatService;

    @Transactional
    public void createMeeting(MeetingRequest request, MultipartFile image, String accessToken) throws IOException {
        User user = this.loginService.findUserByAccessToken(accessToken);
        Meeting meeting = this.createMeetingEntity(user, request, image);
        Meeting result = (Meeting)this.meetingRepository.save(meeting);
        this.chatService.createChatRoom(result);
        this.joinMeeting(result.getMeetingId(), user);
    }

    private Meeting createMeetingEntity(User user, MeetingRequest request, MultipartFile image) throws IOException {
        Meeting meetingEntity = Meeting.createMeetingEntity(request, user);
        String url = this.s3Service.uploadImageToS3(image);
        meetingEntity.updateMeetingImage(url);
        return meetingEntity;
    }

    public MeetingResponse findMeetingByMeetingId(long meetingId) {
        return (MeetingResponse)this.meetingRepository.findMeetingByMeetingId(meetingId).orElseThrow(() -> {
            return new MeetingException(MeetingMessage.NOT_FOUND_MEETING);
        });
    }

    @Transactional
    public void joinMeeting(long meetingId, User user) {
        Meeting meeting = (Meeting)this.meetingRepository.findById(meetingId).orElseThrow(() -> {
            return new MeetingException(MeetingMessage.NOT_FOUND_MEETING);
        });
        if (this.meetingParticipantRepository.findMeetingParticipantByMeetingIdAndUser(meetingId, user).isPresent()) {
            throw new MeetingException(MeetingMessage.ALREADY_PARTICIPATED_MEETING);
        } else {
            this.meetingParticipantRepository.save(MeetingParticipant.createMeetingParticipant(meeting, user));
        }
    }

    public boolean IsMeetingParticipant(long meetingId, String accessToken) {
        User user = this.loginService.findUserByAccessToken(accessToken);
        return this.meetingParticipantRepository.findMeetingParticipantByMeetingIdAndUser(meetingId, user).isPresent();
    }

    public List<MeetingResponse> findAllMeeting(Pageable pageable) {
        return this.meetingRepository.findAllMeeting(pageable);
    }

    public List<MeetingResponse> findMeetingByTitleOrAddress(Pageable pageable, String title) {
        return this.meetingRepository.findMeetingByTitleOrAddress(pageable, title);
    }

    public long findMeetingByTitleOrAddressCount(String data) {
        return this.meetingRepository.countMeetingByTitleOrAddress(data);
    }

    @Transactional
    public void deleteMeetingById(String accessToken, long meetingId) {
        String userPk = this.jwtTokenProvider.getUserPk(accessToken);
        this.checkMeetingOwner(userPk, meetingId);
        Meeting meeting = (Meeting)this.meetingRepository.findById(meetingId).orElseThrow(() -> {
            return new MeetingException(MeetingMessage.NOT_FOUND_MEETING);
        });
        meeting.deleteMeeting();
    }

    public void checkMeetingOwner(String userPk, long meetingId) {
        MeetingMemberResponse result = this.meetingRepository.findMeetingOwnerByMeetingId(meetingId);
        if (!result.getUserId().equals(userPk)) {
            throw new MeetingException(MeetingMessage.CAN_NOT_LEAVE_OWNER);
        }
    }

    @Transactional
    public void modifyMeeting(MeetingRequest meetingRequest, MultipartFile image, String accessToken, long meetingId) throws IOException {
        String userPk = this.jwtTokenProvider.getUserPk(accessToken);
        Meeting meeting = (Meeting)this.meetingRepository.findMeetingByMeetingIdAndMeetingOwner(meetingId, userPk).orElseThrow(() -> {
            return new MeetingException(MeetingMessage.CAN_MODIFY_ONLY_OWNER);
        });
        if (image != null) {
            this.modifyImage(meeting, image);
        }

        meeting.updateData(meetingRequest);
    }

    public void modifyImage(Meeting meeting, MultipartFile image) throws IOException {
        String url = this.s3Service.uploadImageToS3(image);
        this.s3Service.deleteFile(meeting.getMeetingImage());
        meeting.updateMeetingImage(url);
    }

    public List<MeetingResponse> findAllMeetingByUserId(Pageable pageable, String accessToken) {
        String userPk = this.jwtTokenProvider.getUserPk(accessToken);
        return this.meetingParticipantRepository.findAllMeetingByParticipant(pageable, userPk);
    }

    public long findAllMeetingByUserIdCount(String accessToken) {
        String userPk = this.jwtTokenProvider.getUserPk(accessToken);
        return this.meetingParticipantRepository.countAllMeetingByParticipant(userPk);
    }

    public List<ParticipantResponse> findParticipantsByMeetingId(long meetingId) {
        return this.meetingParticipantRepository.findParticipantsByMeetingId(meetingId);
    }

    public MeetingMemberResponse findMeetingMemberByMeetingId(long meetingId) {
        MeetingMemberResponse result = this.meetingRepository.findMeetingOwnerByMeetingId(meetingId);
        result.setParticipantResponses(this.findParticipantsByMeetingId(meetingId));
        return result;
    }

    @Transactional
    public void leaveMeeting(String accessToken, long meetingId) {
        String userPk = this.jwtTokenProvider.getUserPk(accessToken);
        MeetingMemberResponse response = this.meetingRepository.findMeetingOwnerByMeetingId(meetingId);
        if (response.getUserId().equals(userPk)) {
            throw new MeetingException(MeetingMessage.CAN_NOT_LEAVE_OWNER);
        } else {
            this.meetingParticipantRepository.deleteParticipantsByMeetingIdAndUserId(meetingId, userPk);
        }
    }

    public MeetingResponse findMeetingByMeetingIdAndUser(String accessToken, long meetingId) {
        String userPk = this.jwtTokenProvider.getUserPk(accessToken);
        return (MeetingResponse)this.meetingRepository.findMeetingByMeetingIdAndUser(meetingId, userPk).orElseThrow(() -> {
            return new MeetingException(MeetingMessage.CAN_ACCESS_ONLY_OWNER);
        });
    }

    public MeetingService(final MeetingRepository meetingRepository, final S3Service s3Service, final JwtTokenProvider jwtTokenProvider, final LoginService loginService, final MeetingParticipantRepository meetingParticipantRepository, final ChatService chatService) {
        this.meetingRepository = meetingRepository;
        this.s3Service = s3Service;
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginService = loginService;
        this.meetingParticipantRepository = meetingParticipantRepository;
        this.chatService = chatService;
    }
}