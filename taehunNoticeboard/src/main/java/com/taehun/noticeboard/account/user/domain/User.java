package com.taehun.noticeboard.account.user.domain;

import com.taehun.noticeboard.account.profile.domain.Profile;
import com.taehun.noticeboard.account.user.constant.UserRole;
import com.taehun.noticeboard.account.user.constant.UserType;
import com.taehun.noticeboard.account.user.dto.LoginRequest;
import com.taehun.noticeboard.common.config.BooleanConverter;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.meeting.meeting.domain.MeetingParticipant;
import com.taehun.noticeboard.post.post.domain.Post;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@JsonIdentityInfo(
    generator = ObjectIdGenerators.IntSequenceGenerator.class,
    property = "id"
)
public class User implements UserDetails {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long userKey;
    @Column(
        nullable = false,
        length = 30,
        unique = true
    )
    private String id;
    @Column(
        nullable = false,
        unique = true
    )
    private String email;
    @Column(
        nullable = false
    )
    private String password;
    private String profileImage;
    private LocalDate suspensionDate;
    @Column(
        nullable = false
    )
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isSuspension;
    @Column(
        nullable = false
    )
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isDelete;
    private String suspensionReason;
    @Column(
        nullable = false
    )
    private LocalDate lastLoginDate;
    @Enumerated(EnumType.STRING)
    @Column(
        nullable = false
    )
    private UserRole role;
    @Enumerated(EnumType.STRING)
    @Column(
        nullable = false
    )
    private UserType userType;
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @JsonFormat(
        shape = Shape.STRING,
        pattern = "yyyy/MM/dd",
        timezone = "Asia/Seoul"
    )
    @Column(
        nullable = false
    )
    private LocalDate joinDate;
    @OneToOne(
        fetch = FetchType.EAGER,
        cascade = {CascadeType.ALL}
    )
    @JoinColumn(
        nullable = false
    )
    private Profile profile;
    @OneToMany(
        mappedBy = "writer",
        cascade = {CascadeType.ALL}
    )
    private List<Post> post;
    @OneToMany(
        mappedBy = "meetingOwner",
        cascade = {CascadeType.ALL}
    )
    private List<Meeting> meetings;
    @OneToMany(
        mappedBy = "userList",
        cascade = {CascadeType.REMOVE},
        orphanRemoval = true
    )
    private List<MeetingParticipant> participants;

    public void addSuspensionDate(int date, String reason) {
        if (!this.isSuspension || this.suspensionDate == null) {
            this.isSuspension = true;
            this.suspensionDate = LocalDate.now().plusDays((long)date);
            this.suspensionReason = reason;
        }

        this.suspensionDate = this.suspensionDate.plusDays((long)date);
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateLoginDate() {
        this.lastLoginDate = LocalDate.now();
    }

    public void minusSuspensionDate(int date) {
        if (LocalDate.now().compareTo(this.suspensionDate.minusDays((long)date)) > 0) {
            this.isSuspension = false;
            this.suspensionDate = LocalDate.now();
        }

        this.suspensionDate = this.suspensionDate.minusDays((long)date);
    }

    public void deleteUser() {
        this.isDelete = true;
    }

    public static User createGeneralUser(LoginRequest loginRequest, String url, String password) {
        return builder().id(loginRequest.getId()).profileImage(url).password(password).email(loginRequest.getEmail()).profile(Profile.createInitProfileSetting()).lastLoginDate(LocalDate.now()).isSuspension(false).role(UserRole.USER).userType(UserType.GENERAL_USER).isDelete(false).build();
    }

    public static User createOAuthUser(String userId, String email) {
        return builder().id(userId + generateRandomString(6)).email(email).password(UUID.randomUUID().toString()).profile(Profile.createInitProfileSetting()).lastLoginDate(LocalDate.now()).isSuspension(false).role(UserRole.USER).userType(UserType.OAUTH_USER).isDelete(false).build();
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        for(int i = 0; i < length; ++i) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public void addPost(Post post) {
        this.post.add(post);
        post.setWriter(this);
    }

    public boolean checkPassword(String plainPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(plainPassword, this.password);
    }

    public void updateId(String userId) {
        this.id = userId;
    }

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.role.getRole()));
    }

    public String getUsername() {
        return this.id;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public String getPassword() {
        return this.password;
    }

    private static Profile $default$profile() {
        return new Profile();
    }

    private static List<Post> $default$post() {
        return new ArrayList();
    }

    private static List<Meeting> $default$meetings() {
        return new ArrayList();
    }

    private static List<MeetingParticipant> $default$participants() {
        return new LinkedList();
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public long getUserKey() {
        return this.userKey;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getProfileImage() {
        return this.profileImage;
    }

    public LocalDate getSuspensionDate() {
        return this.suspensionDate;
    }

    public boolean isSuspension() {
        return this.isSuspension;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public String getSuspensionReason() {
        return this.suspensionReason;
    }

    public LocalDate getLastLoginDate() {
        return this.lastLoginDate;
    }

    public UserRole getRole() {
        return this.role;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public LocalDate getJoinDate() {
        return this.joinDate;
    }

    public Profile getProfile() {
        return this.profile;
    }

    public List<Post> getPost() {
        return this.post;
    }

    public List<Meeting> getMeetings() {
        return this.meetings;
    }

    public List<MeetingParticipant> getParticipants() {
        return this.participants;
    }

    public User() {
        this.profile = $default$profile();
        this.post = $default$post();
        this.meetings = $default$meetings();
        this.participants = $default$participants();
    }

    public User(final long userKey, final String id, final String email, final String password, final String profileImage, final LocalDate suspensionDate, final boolean isSuspension, final boolean isDelete, final String suspensionReason, final LocalDate lastLoginDate, final UserRole role, final UserType userType, final LocalDate joinDate, final Profile profile, final List<Post> post, final List<Meeting> meetings, final List<MeetingParticipant> participants) {
        this.userKey = userKey;
        this.id = id;
        this.email = email;
        this.password = password;
        this.profileImage = profileImage;
        this.suspensionDate = suspensionDate;
        this.isSuspension = isSuspension;
        this.isDelete = isDelete;
        this.suspensionReason = suspensionReason;
        this.lastLoginDate = lastLoginDate;
        this.role = role;
        this.userType = userType;
        this.joinDate = joinDate;
        this.profile = profile;
        this.post = post;
        this.meetings = meetings;
        this.participants = participants;
    }

    public void setRole(final UserRole role) {
        this.role = role;
    }

    public static class UserBuilder {
        private long userKey;
        private String id;
        private String email;
        private String password;
        private String profileImage;
        private LocalDate suspensionDate;
        private boolean isSuspension;
        private boolean isDelete;
        private String suspensionReason;
        private LocalDate lastLoginDate;
        private UserRole role;
        private UserType userType;
        private LocalDate joinDate;
        private boolean profile$set;
        private Profile profile$value;
        private boolean post$set;
        private List<Post> post$value;
        private boolean meetings$set;
        private List<Meeting> meetings$value;
        private boolean participants$set;
        private List<MeetingParticipant> participants$value;

        UserBuilder() {
        }

        public UserBuilder userKey(final long userKey) {
            this.userKey = userKey;
            return this;
        }

        public UserBuilder id(final String id) {
            this.id = id;
            return this;
        }

        public UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public UserBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public UserBuilder profileImage(final String profileImage) {
            this.profileImage = profileImage;
            return this;
        }

        public UserBuilder suspensionDate(final LocalDate suspensionDate) {
            this.suspensionDate = suspensionDate;
            return this;
        }

        public UserBuilder isSuspension(final boolean isSuspension) {
            this.isSuspension = isSuspension;
            return this;
        }

        public UserBuilder isDelete(final boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        public UserBuilder suspensionReason(final String suspensionReason) {
            this.suspensionReason = suspensionReason;
            return this;
        }

        public UserBuilder lastLoginDate(final LocalDate lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
            return this;
        }

        public UserBuilder role(final UserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder userType(final UserType userType) {
            this.userType = userType;
            return this;
        }

        @JsonFormat(
            shape = Shape.STRING,
            pattern = "yyyy/MM/dd",
            timezone = "Asia/Seoul"
        )
        public UserBuilder joinDate(final LocalDate joinDate) {
            this.joinDate = joinDate;
            return this;
        }

        public UserBuilder profile(final Profile profile) {
            this.profile$value = profile;
            this.profile$set = true;
            return this;
        }

        public UserBuilder post(final List<Post> post) {
            this.post$value = post;
            this.post$set = true;
            return this;
        }

        public UserBuilder meetings(final List<Meeting> meetings) {
            this.meetings$value = meetings;
            this.meetings$set = true;
            return this;
        }

        public UserBuilder participants(final List<MeetingParticipant> participants) {
            this.participants$value = participants;
            this.participants$set = true;
            return this;
        }

        public User build() {
            Profile profile$value = this.profile$value;
            if (!this.profile$set) {
                profile$value = User.$default$profile();
            }

            List<Post> post$value = this.post$value;
            if (!this.post$set) {
                post$value = User.$default$post();
            }

            List<Meeting> meetings$value = this.meetings$value;
            if (!this.meetings$set) {
                meetings$value = User.$default$meetings();
            }

            List<MeetingParticipant> participants$value = this.participants$value;
            if (!this.participants$set) {
                participants$value = User.$default$participants();
            }

            return new User(this.userKey, this.id, this.email, this.password, this.profileImage, this.suspensionDate, this.isSuspension, this.isDelete, this.suspensionReason, this.lastLoginDate, this.role, this.userType, this.joinDate, profile$value, post$value, meetings$value, participants$value);
        }

        public String toString() {
            return "User.UserBuilder(userKey=" + this.userKey + ", id=" + this.id + ", email=" + this.email + ", password=" + this.password + ", profileImage=" + this.profileImage + ", suspensionDate=" + this.suspensionDate + ", isSuspension=" + this.isSuspension + ", isDelete=" + this.isDelete + ", suspensionReason=" + this.suspensionReason + ", lastLoginDate=" + this.lastLoginDate + ", role=" + this.role + ", userType=" + this.userType + ", joinDate=" + this.joinDate + ", profile$value=" + this.profile$value + ", post$value=" + this.post$value + ", meetings$value=" + this.meetings$value + ", participants$value=" + this.participants$value + ")";
        }
    }
}
