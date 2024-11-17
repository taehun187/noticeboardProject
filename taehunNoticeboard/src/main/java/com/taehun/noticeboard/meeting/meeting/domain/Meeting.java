package com.taehun.noticeboard.meeting.meeting.domain;

import com.taehun.noticeboard.account.user.domain.User;
import com.taehun.noticeboard.common.config.BooleanConverter;
import com.taehun.noticeboard.meeting.chat.entity.Chat;
import com.taehun.noticeboard.meeting.chat.entity.ChatRoom;
import com.taehun.noticeboard.meeting.meeting.dto.MeetingRequest;
import com.taehun.noticeboard.meeting.reservation.entity.Reservation;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Meeting {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long meetingId;
    @ManyToOne(
        fetch = FetchType.LAZY
    )
    private User meetingOwner;
    @OneToMany(
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        mappedBy = "meetings",
        orphanRemoval = true
    )
    private List<Reservation> reservations;
    @OneToOne(
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private ChatRoom chatRoom;
    @Column(
        nullable = false
    )
    private double locateX;
    @Column(
        nullable = false
    )
    private double locateY;
    @Column(
        nullable = false
    )
    private String address;
    private String meetingImage;
    private String title;
    private String description;
    private String detailAddress;
    private int maxParticipants;
    @Column(
        nullable = false
    )
    @Convert(
        converter = BooleanConverter.class
    )
    private boolean isDelete;
    @OneToMany(
        mappedBy = "meetingList",
        cascade = {CascadeType.REMOVE},
        orphanRemoval = true
    )
    private List<MeetingParticipant> meetingParticipants;
    @OneToMany(
        mappedBy = "meetingId",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private List<Chat> chats;

    public static Meeting createMeetingEntity(MeetingRequest meetingRequest, User user) {
        return builder().meetingOwner(user).locateY(meetingRequest.getLocateY()).locateX(meetingRequest.getLocateX()).address(meetingRequest.getAddress()).title(meetingRequest.getTitle()).description(meetingRequest.getDescription()).maxParticipants(meetingRequest.getMaxParticipants()).detailAddress(meetingRequest.getDetailAddress()).isDelete(false).build();
    }

    public void updateMeetingImage(String image) {
        this.meetingImage = image;
    }

    public void updateData(MeetingRequest meetingRequest) {
        this.locateX = meetingRequest.getLocateX();
        this.locateY = meetingRequest.getLocateY();
        this.title = meetingRequest.getTitle();
        this.address = meetingRequest.getAddress();
        this.description = meetingRequest.getDescription();
        this.detailAddress = meetingRequest.getDetailAddress();
        this.maxParticipants = meetingRequest.getMaxParticipants();
    }

    public void addReservation(Reservation reservation) {
        this.reservations.add(reservation);
        reservation.setMeetings(this);
    }

    public void deleteMeeting() {
        this.isDelete = true;
    }

    private static List<Reservation> $default$reservations() {
        return new ArrayList();
    }

    private static List<MeetingParticipant> $default$meetingParticipants() {
        return new ArrayList();
    }

    private static List<Chat> $default$chats() {
        return new ArrayList();
    }

    public static MeetingBuilder builder() {
        return new MeetingBuilder();
    }

    public long getMeetingId() {
        return this.meetingId;
    }

    public User getMeetingOwner() {
        return this.meetingOwner;
    }

    public List<Reservation> getReservations() {
        return this.reservations;
    }

    public ChatRoom getChatRoom() {
        return this.chatRoom;
    }

    public double getLocateX() {
        return this.locateX;
    }

    public double getLocateY() {
        return this.locateY;
    }

    public String getAddress() {
        return this.address;
    }

    public String getMeetingImage() {
        return this.meetingImage;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDetailAddress() {
        return this.detailAddress;
    }

    public int getMaxParticipants() {
        return this.maxParticipants;
    }

    public boolean isDelete() {
        return this.isDelete;
    }

    public List<MeetingParticipant> getMeetingParticipants() {
        return this.meetingParticipants;
    }

    public List<Chat> getChats() {
        return this.chats;
    }

    protected Meeting(final long meetingId, final User meetingOwner, final List<Reservation> reservations, final ChatRoom chatRoom, final double locateX, final double locateY, final String address, final String meetingImage, final String title, final String description, final String detailAddress, final int maxParticipants, final boolean isDelete, final List<MeetingParticipant> meetingParticipants, final List<Chat> chats) {
        this.meetingId = meetingId;
        this.meetingOwner = meetingOwner;
        this.reservations = reservations;
        this.chatRoom = chatRoom;
        this.locateX = locateX;
        this.locateY = locateY;
        this.address = address;
        this.meetingImage = meetingImage;
        this.title = title;
        this.description = description;
        this.detailAddress = detailAddress;
        this.maxParticipants = maxParticipants;
        this.isDelete = isDelete;
        this.meetingParticipants = meetingParticipants;
        this.chats = chats;
    }

    protected Meeting() {
        this.reservations = $default$reservations();
        this.meetingParticipants = $default$meetingParticipants();
        this.chats = $default$chats();
    }

    public void setChatRoom(final ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public static class MeetingBuilder {
        private long meetingId;
        private User meetingOwner;
        private boolean reservations$set;
        private List<Reservation> reservations$value;
        private ChatRoom chatRoom;
        private double locateX;
        private double locateY;
        private String address;
        private String meetingImage;
        private String title;
        private String description;
        private String detailAddress;
        private int maxParticipants;
        private boolean isDelete;
        private boolean meetingParticipants$set;
        private List<MeetingParticipant> meetingParticipants$value;
        private boolean chats$set;
        private List<Chat> chats$value;

        MeetingBuilder() {
        }

        public MeetingBuilder meetingId(final long meetingId) {
            this.meetingId = meetingId;
            return this;
        }

        public MeetingBuilder meetingOwner(final User meetingOwner) {
            this.meetingOwner = meetingOwner;
            return this;
        }

        public MeetingBuilder reservations(final List<Reservation> reservations) {
            this.reservations$value = reservations;
            this.reservations$set = true;
            return this;
        }

        public MeetingBuilder chatRoom(final ChatRoom chatRoom) {
            this.chatRoom = chatRoom;
            return this;
        }

        public MeetingBuilder locateX(final double locateX) {
            this.locateX = locateX;
            return this;
        }

        public MeetingBuilder locateY(final double locateY) {
            this.locateY = locateY;
            return this;
        }

        public MeetingBuilder address(final String address) {
            this.address = address;
            return this;
        }

        public MeetingBuilder meetingImage(final String meetingImage) {
            this.meetingImage = meetingImage;
            return this;
        }

        public MeetingBuilder title(final String title) {
            this.title = title;
            return this;
        }

        public MeetingBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public MeetingBuilder detailAddress(final String detailAddress) {
            this.detailAddress = detailAddress;
            return this;
        }

        public MeetingBuilder maxParticipants(final int maxParticipants) {
            this.maxParticipants = maxParticipants;
            return this;
        }

        public MeetingBuilder isDelete(final boolean isDelete) {
            this.isDelete = isDelete;
            return this;
        }

        public MeetingBuilder meetingParticipants(final List<MeetingParticipant> meetingParticipants) {
            this.meetingParticipants$value = meetingParticipants;
            this.meetingParticipants$set = true;
            return this;
        }

        public MeetingBuilder chats(final List<Chat> chats) {
            this.chats$value = chats;
            this.chats$set = true;
            return this;
        }

        public Meeting build() {
            List<Reservation> reservations$value = this.reservations$value;
            if (!this.reservations$set) {
                reservations$value = Meeting.$default$reservations();
            }

            List<MeetingParticipant> meetingParticipants$value = this.meetingParticipants$value;
            if (!this.meetingParticipants$set) {
                meetingParticipants$value = Meeting.$default$meetingParticipants();
            }

            List<Chat> chats$value = this.chats$value;
            if (!this.chats$set) {
                chats$value = Meeting.$default$chats();
            }

            return new Meeting(this.meetingId, this.meetingOwner, reservations$value, this.chatRoom, this.locateX, this.locateY, this.address, this.meetingImage, this.title, this.description, this.detailAddress, this.maxParticipants, this.isDelete, meetingParticipants$value, chats$value);
        }

        public String toString() {
            return "Meeting.MeetingBuilder(meetingId=" + this.meetingId + ", meetingOwner=" + this.meetingOwner + ", reservations$value=" + this.reservations$value + ", chatRoom=" + this.chatRoom + ", locateX=" + this.locateX + ", locateY=" + this.locateY + ", address=" + this.address + ", meetingImage=" + this.meetingImage + ", title=" + this.title + ", description=" + this.description + ", detailAddress=" + this.detailAddress + ", maxParticipants=" + this.maxParticipants + ", isDelete=" + this.isDelete + ", meetingParticipants$value=" + this.meetingParticipants$value + ", chats$value=" + this.chats$value + ")";
        }
    }
}