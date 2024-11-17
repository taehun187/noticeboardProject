package com.taehun.noticeboard.account.user.domain;

import com.taehun.noticeboard.account.profile.domain.QProfile;
import com.taehun.noticeboard.account.user.constant.UserRole;
import com.taehun.noticeboard.account.user.constant.UserType;
import com.taehun.noticeboard.meeting.meeting.domain.Meeting;
import com.taehun.noticeboard.meeting.meeting.domain.MeetingParticipant;
import com.taehun.noticeboard.meeting.meeting.domain.QMeeting;
import com.taehun.noticeboard.meeting.meeting.domain.QMeetingParticipant;
import com.taehun.noticeboard.post.post.domain.Post;
import com.taehun.noticeboard.post.post.domain.QPost;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.PathMetadataFactory;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;
import java.time.LocalDate;

public class QUser extends EntityPathBase<User> {
    private static final long serialVersionUID = -1055719676L;
    private static final PathInits INITS;
    public static final QUser user;
    public final StringPath email;
    public final StringPath id;
    public final BooleanPath isDelete;
    public final BooleanPath isSuspension;
    public final DatePath<LocalDate> joinDate;
    public final DatePath<LocalDate> lastLoginDate;
    public final ListPath<Meeting, QMeeting> meetings;
    public final ListPath<MeetingParticipant, QMeetingParticipant> participants;
    public final StringPath password;
    public final ListPath<Post, QPost> post;
    public final QProfile profile;
    public final StringPath profileImage;
    public final EnumPath<UserRole> role;
    public final DatePath<LocalDate> suspensionDate;
    public final StringPath suspensionReason;
    public final NumberPath<Long> userKey;
    public final EnumPath<UserType> userType;

    public QUser(String variable) {
        this(User.class, PathMetadataFactory.forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUser(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUser(PathMetadata metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.email = this.createString("email");
        this.id = this.createString("id");
        this.isDelete = this.createBoolean("isDelete");
        this.isSuspension = this.createBoolean("isSuspension");
        this.joinDate = this.createDate("joinDate", LocalDate.class);
        this.lastLoginDate = this.createDate("lastLoginDate", LocalDate.class);
        this.meetings = this.createList("meetings", Meeting.class, QMeeting.class, PathInits.DIRECT2);
        this.participants = this.createList("participants", MeetingParticipant.class, QMeetingParticipant.class, PathInits.DIRECT2);
        this.password = this.createString("password");
        this.post = this.createList("post", Post.class, QPost.class, PathInits.DIRECT2);
        this.profileImage = this.createString("profileImage");
        this.role = this.createEnum("role", UserRole.class);
        this.suspensionDate = this.createDate("suspensionDate", LocalDate.class);
        this.suspensionReason = this.createString("suspensionReason");
        this.userKey = this.createNumber("userKey", Long.class);
        this.userType = this.createEnum("userType", UserType.class);
        this.profile = inits.isInitialized("profile") ? new QProfile(this.forProperty("profile")) : null;
    }

    static {
        INITS = PathInits.DIRECT2;
        user = new QUser("user");
    }
}
