package com.taehun.noticeboard.post.comment.service;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.service.LoginService;
import com.taehun.noticeboard.common.response.message.CommentMessage;
import com.taehun.noticeboard.common.response.ResponseCode;
import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.post.comment.domain.Comment;
import com.taehun.noticeboard.post.comment.dto.CommentRequest;
import com.taehun.noticeboard.post.comment.dto.CommentResponse;
import com.taehun.noticeboard.post.comment.exception.CommentException;
import com.taehun.noticeboard.post.comment.repository.CommentRepository;
import com.taehun.noticeboard.post.post.domain.Post;
import com.taehun.noticeboard.post.post.service.PostService;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoginService loginService;

    public ResponseMessage<List<Comment>> getCommit(Long postId) {
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, this.commentRepository.findCommentByPostId(postId));
    }

    @Transactional
    public ResponseMessage addComment(CommentRequest commentRequest, String token) {
        User user = this.loginService.findUserByAccessToken(token);
        Post post = this.postService.findPostById(commentRequest.getPostId());
        Comment comment = Comment.createComment(commentRequest, user);
        post.addFreeCommit(comment);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    @Transactional
    public ResponseMessage deleteCommentByCommentId(long commentId, String token) {
        String userId = this.jwtTokenProvider.getUserPk(token);
        Comment comment = (Comment)this.commentRepository.findCommentByCommentIdAndUserId(commentId, userId).orElseThrow(() -> {
            return new CommentException(CommentMessage.ONLY_OWNER_CAN_DELETE);
        });
        comment.deleteComment();
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    public ResponseMessage<List<CommentResponse>> getNotificationFromUser(String token) {
        User user = this.loginService.findUserByAccessToken(token);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, this.commentRepository.findCommentByCommentPostWithoutMe(user));
    }

    public CommentService(final PostService postService, final CommentRepository commentRepository, final JwtTokenProvider jwtTokenProvider, final LoginService loginService) {
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.loginService = loginService;
    }
}