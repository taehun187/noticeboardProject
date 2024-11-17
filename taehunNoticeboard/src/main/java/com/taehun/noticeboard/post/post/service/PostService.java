package com.taehun.noticeboard.post.post.service;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.account.user.service.LoginService;
import com.taehun.noticeboard.common.response.message.PostMessage;
import com.taehun.noticeboard.common.response.ResponseCode;
import com.taehun.noticeboard.common.response.ResponseMessage;
import com.taehun.noticeboard.post.attachment.repository.AttachmentRepository;
import com.taehun.noticeboard.post.attachment.service.AttachmentService;
import com.taehun.noticeboard.post.post.domain.Post;
import com.taehun.noticeboard.post.post.dto.PostListResponse;
import com.taehun.noticeboard.post.post.dto.PostRequest;
import com.taehun.noticeboard.post.post.dto.PostResponse;
import com.taehun.noticeboard.post.post.exception.PostException;
import com.taehun.noticeboard.post.post.repository.PostRepository;
import com.taehun.noticeboard.security.jwt.support.JwtTokenProvider;
import java.io.IOException;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentService attachmentService;

    @Transactional
    public ResponseMessage addPost(PostRequest postRequest, List<MultipartFile> multipartFiles, String token) throws IOException {
        User user = this.loginService.findUserByAccessToken(token);
        Post post = this.createNewPost(postRequest);
        user.addPost(post);
        this.attachmentService.fileUpload(multipartFiles, post);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    public Post createNewPost(PostRequest postRequest) {
        Post post = Post.createPost(postRequest);
        post.addTagFromTagList(postRequest.getTags());
        return post;
    }

    @Transactional
    public ResponseMessage deletePost(long postId, String token) {
        String userId = this.jwtTokenProvider.getUserPk(token);
        Post post = this.findPostById(postId);
        this.isPostOwner(post, userId);
        post.deletePost();
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    private void isPostOwner(Post post, String userId) {
        if (!post.getWriter().getId().equals(userId)) {
            new PostException(PostMessage.ONLY_OWNER_CAN_DELETE);
        }

    }

    @Transactional
    public ResponseMessage increasePostLike(long postId, String token) {
        User user = this.loginService.findUserByAccessToken(token);
        this.postRepository.findRecommendationFromPost(postId, user.getId()).ifPresent((a) -> {
            throw new PostException(PostMessage.ALREADY_RECOMMENDED);
        });
        Post post = this.findPostById(postId);
        post.addRecommendation(user);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    @Transactional
    public ResponseMessage updatePost(long postId, String token, PostRequest postRequest, List<MultipartFile> multipartFiles) throws IOException {
        String userId = this.jwtTokenProvider.getUserPk(token);
        Post post = this.findPostByIdAndValidateOwnership(postId, userId);
        post.updatePost(postRequest);
        this.uploadAttachments(multipartFiles, post);
        this.deleteAttachments(postRequest.getDeletedFileIds(), postId);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    public Post findPostByIdAndValidateOwnership(long postId, String userId) {
        Post post = this.findPostById(postId);
        if (!post.getWriter().getId().equals(userId)) {
            new PostException(PostMessage.ONLY_OWNER_CAN_MODIFY);
        }

        return post;
    }

    public void deleteAttachments(long[] deletedFileIds, long postId) {
        this.attachmentService.deletedAttachment(deletedFileIds, postId);
    }

    public void uploadAttachments(List<MultipartFile> multipartFiles, Post post) throws IOException {
        this.attachmentService.fileUpload(multipartFiles, post);
    }

    public ResponseMessage<List<PostListResponse>> getAllPost(Pageable pageable) {
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, this.postRepository.getPostList(pageable));
    }

    public ResponseMessage<PostResponse> findPostByPostId(long postId) {
        Post post = this.findPostById(postId);
        PostResponse postResponse = PostResponse.createPostResponse(post);
        postResponse.addTagData(this.postRepository.findTagsInPostId(postId));
        postResponse.setAttachment(this.attachmentRepository.findAttachmentsByPostId(postId));
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, postResponse);
    }

    @Transactional
    public ResponseMessage updatePostView(long postId) {
        this.postRepository.updatePostView(postId);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS);
    }

    public ResponseMessage<Long> getTotalNumberOfPosts(String type, String data) {
        long result = this.getTotalNumberAccordingType(type, data);
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, result);
    }

    public long getTotalNumberAccordingType(String type, String data) {
        if (type.equals("normal")) {
            return this.postRepository.getTotalNumberOfPosts();
        } else if (type.equals("tag")) {
            return this.postRepository.getTotalNumberOfTagSearchPosts(data);
        } else {
            return type.equals("search") ? this.postRepository.getTotalNumberOfSearchPosts(data) : 0L;
        }
    }

    public ResponseMessage findPostBySearch(Pageable pageable, String postContent) {
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, this.postRepository.findPostBySearch(pageable, postContent));
    }

    public ResponseMessage findPostBySearchAndTag(Pageable pageable, String tag) {
        return ResponseMessage.of(ResponseCode.REQUEST_SUCCESS, this.postRepository.findPostByTag(pageable, tag));
    }

    public Post findPostById(long postId) {
        Post post = (Post)this.postRepository.findPostByPostId(postId).orElseThrow(() -> {
            return new PostException(PostMessage.NOT_FOUNT_POST);
        });
        this.isDeletedPost(post);
        return post;
    }

    private void isDeletedPost(Post post) {
        if (post.isDelete()) {
            throw new PostException(PostMessage.IS_DELETE_POST);
        }
    }

    public PostService(final PostRepository postRepository, final LoginService loginService, final JwtTokenProvider jwtTokenProvider, final AttachmentRepository attachmentRepository, final AttachmentService attachmentService) {
        this.postRepository = postRepository;
        this.loginService = loginService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.attachmentRepository = attachmentRepository;
        this.attachmentService = attachmentService;
    }
}