package com.taehun.noticeboard.account.user.service;

import com.taehun.noticeboard.account.user.constant.UserType;
import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.dto.LoginRequest;
import com.taehun.noticeboard.account.user.dto.PasswordRequest;
import com.taehun.noticeboard.account.user.dto.UserResponse;
import com.taehun.noticeboard.account.user.exception.LoginException;
import com.taehun.noticeboard.account.user.repository.LoginRepository;
import com.taehun.noticeboard.common.response.ResponseCode;
import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.common.response.message.AccountMessage;
import com.taehun.noticeboard.post.post.repository.PostRepository;
import com.taehun.noticeboard.s3.service.S3Service;
import com.taehun.noticeboard.security.jwt.dto.Token;
import com.taehun.noticeboard.security.jwt.service.JwtService;
import com.taehun.noticeboard.security.jwt.support.CookieSupport;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class LoginService {
    private final LoginRepository loginRepository;
    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final S3Service s3Service;
    private final JwtService jwtService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void validNewAccountVerification(LoginRequest loginRequest) {
        if (this.loginRepository.findById(loginRequest.getId()).isPresent()) {
            throw new LoginException(AccountMessage.EXISTS_ACCOUNT);
        } else if (!loginRequest.getPassword().equals(loginRequest.getCheckPassword())) {
            throw new LoginException(AccountMessage.NOT_MATCH_PASSWORD);
        }
    }

    public ResponseMessage register(LoginRequest loginRequest, MultipartFile multipartFile) throws IOException {
        this.validNewAccountVerification(loginRequest);
        String url = this.s3Service.uploadImageToS3(multipartFile);
        this.loginRepository.save(User.createGeneralUser(loginRequest, url, this.bCryptPasswordEncoder.encode(loginRequest.getPassword())));
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    public void isValidAccount(LoginRequest request, User user) {
        if (user.getUserType().equals(UserType.OAUTH_USER)) {
            throw new LoginException(AccountMessage.NOT_FOUNT_ACCOUNT);
        } else if (!user.checkPassword(request.getPassword(), this.bCryptPasswordEncoder)) {
            throw new LoginException(AccountMessage.NOT_MATCH_PASSWORD);
        } else if (user.isDelete()) {
            throw new LoginException(AccountMessage.IS_DELETE_ACCOUNT);
        } else if (user.isSuspension() && user.getSuspensionDate().compareTo(LocalDate.now()) > 0) {
            LocalDate var10002 = user.getSuspensionDate();
            throw new LoginException("해당 계정은 " + var10002 + " 일 까지 정지입니다. \n사유 : " + user.getSuspensionReason());
        }
    }

    public User login(LoginRequest loginRequest, HttpServletResponse response) {
        User result = this.findUserById(loginRequest.getId());
        this.isValidAccount(loginRequest, result);
        result.updateLoginDate();
        this.createJwtToken(result, response);
        return result;
    }

    public void createJwtToken(User user, HttpServletResponse response) {
        Token token = this.jwtTokenProvider.createJwtToken(user.getUsername(), user.getRole());
        this.jwtService.login(token);
        CookieSupport.setCookieFromJwt(token, response);
    }

    public void isExistAccount(String userId, String id) {
        if (this.loginRepository.findByEmail(userId).isPresent()) {
            throw new LoginException(AccountMessage.EXISTS_EMAIL);
        } else if (this.loginRepository.findById(id).isPresent()) {
            throw new LoginException(AccountMessage.EXISTS_ID);
        }
    }

    public ResponseMessage findUserByToken(String token) {
        UserResponse userResponse = UserResponse.createResponse(this.findUserByAccessToken(token));
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, userResponse);
    }

    public ResponseMessage removeUser(String accessToken, HttpServletResponse response) {
        User result = this.findUserByAccessToken(accessToken);
        this.deleteAllS3FilesUploadedByUserId(result.getId());
        result.deleteUser();
        CookieSupport.deleteJwtTokenInCookie(response);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    public void deleteAllS3FilesUploadedByUserId(String userId) {
        List<Long> postIds = this.postRepository.findPostIdByUserId(userId);
        Iterator var3 = postIds.iterator();

        while(var3.hasNext()) {
            long postId = (Long)var3.next();
            this.s3Service.deleteFileByPostId(postId);
        }

    }

    @Transactional
    public String modifyProfileImage(String accessToken, MultipartFile multipartFile) throws IOException {
        User result = this.findUserByAccessToken(accessToken);
        if (result.getProfileImage() != null && !result.getProfileImage().isEmpty()) {
            this.s3Service.deleteFile(result.getProfileImage());
        }

        String url = this.s3Service.uploadImageToS3(multipartFile);
        result.updateProfileImage(url);
        return url;
    }

    public User findUserByAccessToken(String accessToken) {
        String userId = this.jwtTokenProvider.getUserPk(accessToken);
        return this.findUserById(userId);
    }

    public User findUserById(String userId) {
        return (User)this.loginRepository.findById(userId).orElseThrow(() -> {
            return new LoginException(AccountMessage.NOT_FOUNT_ACCOUNT);
        });
    }

    @Transactional
    public void modifyPassword(PasswordRequest request) {
        User user = (User)this.loginRepository.findByEmail(request.getEmail()).orElseThrow(() -> {
            return new LoginException(AccountMessage.NOT_FOUNT_ACCOUNT);
        });
        user.updatePassword(this.bCryptPasswordEncoder.encode(request.getPassword()));
    }

    public LoginService(final LoginRepository loginRepository, final PostRepository postRepository, final JwtTokenProvider jwtTokenProvider, final S3Service s3Service, final JwtService jwtService, final BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.loginRepository = loginRepository;
        this.postRepository = postRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.s3Service = s3Service;
        this.jwtService = jwtService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
}
