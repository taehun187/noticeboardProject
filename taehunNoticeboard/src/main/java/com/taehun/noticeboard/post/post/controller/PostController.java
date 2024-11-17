package com.taehun.noticeboard.post.post.controller;

import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.post.post.dto.PostRequest;
import com.taehun.noticeboard.post.post.service.PostService;
import java.io.IOException;
import java.util.List;
import javax.security.auth.login.AccountException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping({"/posts"})
public class PostController {
    private final PostService postService;

    @PostMapping(
        consumes = {"application/json", "multipart/form-data"}
    )
    public ResponseEntity<ResponseMessage> addPost(@RequestPart PostRequest postRequest, @RequestPart(required = false) List<MultipartFile> multipartFiles, @CookieValue String accessToken) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.postService.addPost(postRequest, multipartFiles, accessToken));
    }

    @GetMapping({"/count"})
    public ResponseEntity<ResponseMessage> getTotalNumberOfPosts(@RequestParam String type, @RequestParam(required = false) String data) {
        return ResponseEntity.ok().body(this.postService.getTotalNumberOfPosts(type, data));
    }

    @GetMapping
    public ResponseEntity<ResponseMessage> getAllPost(Pageable pageable) {
        return ResponseEntity.ok().body(this.postService.getAllPost(pageable));
    }

    @GetMapping({"/{postId}"})
    public ResponseEntity<ResponseMessage> selectOnePost(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok().body(this.postService.findPostByPostId(postId));
    }

    @DeleteMapping({"/{postId}"})
    public ResponseEntity<ResponseMessage> deletePost(@PathVariable("postId") Long postId, @CookieValue String accessToken) {
        return ResponseEntity.ok().body(this.postService.deletePost(postId, accessToken));
    }

    @PutMapping({"/{postId}"})
    public ResponseEntity<ResponseMessage> updatePost(@PathVariable("postId") Long postId, @CookieValue String accessToken, @RequestPart PostRequest postRequest, @RequestPart(required = false) List<MultipartFile> multipartFiles) throws IOException {
        return ResponseEntity.ok().body(this.postService.updatePost(postId, accessToken, postRequest, multipartFiles));
    }

    @PatchMapping({"/views/{postId}"})
    public ResponseEntity<ResponseMessage> increasePostViews(@PathVariable long postId) {
        return ResponseEntity.ok().body(this.postService.updatePostView(postId));
    }

    @PatchMapping({"/likes/{postId}"})
    public ResponseEntity<ResponseMessage> increasePostLike(@PathVariable Long postId, @CookieValue String accessToken) throws AccountException {
        return ResponseEntity.ok().body(this.postService.increasePostLike(postId, accessToken));
    }

    @GetMapping({"/search/{postContent}"})
    public ResponseEntity<ResponseMessage> findPostBySearch(Pageable pageable, @PathVariable String postContent) {
        return ResponseEntity.ok().body(this.postService.findPostBySearch(pageable, postContent));
    }

    @GetMapping({"/search/tags/{tagData}"})
    public ResponseEntity<ResponseMessage> findPostByTag(Pageable pageable, @PathVariable String tagData) {
        return ResponseEntity.ok().body(this.postService.findPostBySearchAndTag(pageable, tagData));
    }

    public PostController(final PostService postService) {
        this.postService = postService;
    }
}