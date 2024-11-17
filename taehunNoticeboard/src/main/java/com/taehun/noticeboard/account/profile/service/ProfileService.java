package com.taehun.noticeboard.account.profile.service;

import com.taehun.noticeboard.account.profile.dto.ProfileRequest;
import com.taehun.noticeboard.account.profile.dto.ProfileResponse;
import com.taehun.noticeboard.account.profile.dto.StatisticsResponse;
import com.taehun.noticeboard.account.profile.repository.ProfileRepository;
import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.exception.LoginException;
import com.taehun.noticeboard.account.user.repository.LoginRepository;
import com.taehun.noticeboard.account.user.service.LoginService;
import com.taehun.noticeboard.common.response.ResponseCode;
import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.common.response.message.AccountMessage;
import com.taehun.noticeboard.security.jwt.support.CookieSupport;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final LoginRepository loginRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoginService loginService;

    public ResponseMessage<StatisticsResponse> getStatistics(String accessToken) {
        String userId = this.jwtTokenProvider.getUserPk(accessToken);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, this.profileRepository.getStatisticsOfUser(userId));
    }

    @Transactional
    public ResponseMessage updateProfile(ProfileRequest profileRequest, String token, HttpServletResponse response) {
        User user = this.loginService.findUserByAccessToken(token);
        if (!profileRequest.getUserId().equals(user.getId())) {
            if (this.loginRepository.findById(profileRequest.getUserId()).isPresent()) {
                throw new LoginException(AccountMessage.EXISTS_ID);
            }

            user.updateId(profileRequest.getUserId());
            CookieSupport.deleteJwtTokenInCookie(response);
        }

        user.getProfile().updateProfile(profileRequest);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    public ResponseMessage getProfileFromUser(String token) {
        User result = this.loginService.findUserByAccessToken(token);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, ProfileResponse.createProfileResponse(result.getProfile(), result));
    }

    public ProfileService(final ProfileRepository profileRepository, final LoginRepository loginRepository, final JwtTokenProvider jwtTokenProvider, final LoginService loginService) {
        this.profileRepository = profileRepository;
        this.loginRepository = loginRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginService = loginService;
    }
}
