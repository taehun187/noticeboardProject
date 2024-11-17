package com.taehun.noticeboard.post.comment.controller;

import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.post.comment.dto.CommentRequest;
import com.taehun.noticeboard.post.comment.service.CommentService;
import javax.security.auth.login.AccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/comments"})
public class CommentController {
    private final CommentService commentService;

    @GetMapping({"/{postId}"})
    public ResponseEntity<ResponseMessage> getComment(@PathVariable Long postId) {
        return ResponseEntity.ok().body(this.commentService.getCommit(postId));
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> addComment(@RequestBody CommentRequest commentRequest, @CookieValue String accessToken) throws AccountException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.commentService.addComment(commentRequest, accessToken));
    }

    @DeleteMapping({"/{commentId}"})
    public ResponseEntity<ResponseMessage> deleteComment(@PathVariable long commentId, @CookieValue String accessToken) {
        return ResponseEntity.ok().body(this.commentService.deleteCommentByCommentId(commentId, accessToken));
    }

    @GetMapping({"/notifications"})
    public ResponseEntity<ResponseMessage> getNotificationFromUser(@CookieValue String accessToken) throws AccountException {
        return ResponseEntity.ok().body(this.commentService.getNotificationFromUser(accessToken));
    }

    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }
}