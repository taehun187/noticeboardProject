package com.taehun.noticeboard.account.profile.repository;

import com.taehun.noticeboard.account.profile.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long>, CustomProfileRepository {
}