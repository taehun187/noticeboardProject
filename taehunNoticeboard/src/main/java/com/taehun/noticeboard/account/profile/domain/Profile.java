package com.taehun.noticeboard.account.profile.domain;

import com.taehun.noticeboard.account.profile.dto.ProfileRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profile {
    @Id
    @GeneratedValue(
        strategy = GenerationType.IDENTITY
    )
    private long profileKey;
    private String phone;
    @Column(
        nullable = false
    )
    private int options;

    public Profile() {
    }

    public static Profile createInitProfileSetting() {
        Profile profile = new Profile();
        profile.setOptions(1);
        return profile;
    }

    public void updateProfile(ProfileRequest profileRequest) {
        this.phone = profileRequest.getPhone();
        this.options = profileRequest.getOptions();
    }

    public long getProfileKey() {
        return this.profileKey;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getOptions() {
        return this.options;
    }

    public void setProfileKey(final long profileKey) {
        this.profileKey = profileKey;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setOptions(final int options) {
        this.options = options;
    }
}
