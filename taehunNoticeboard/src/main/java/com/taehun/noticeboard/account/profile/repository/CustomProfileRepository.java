package com.taehun.noticeboard.account.profile.repository;

import com.taehun.noticeboard.account.profile.dto.StatisticsResponse;

public interface CustomProfileRepository {
    StatisticsResponse getStatisticsOfUser(String userId);
}