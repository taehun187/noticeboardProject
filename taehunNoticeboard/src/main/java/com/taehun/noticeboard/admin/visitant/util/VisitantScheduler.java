package com.taehun.noticeboard.admin.visitant.util;

import com.taehun.noticeboard.admin.visitant.domain.Visitant;
import com.taehun.noticeboard.admin.visitant.repository.VisitantRepository;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class VisitantScheduler {
    private final RedisTemplate<String, String> redisTemplate;
    private final VisitantRepository visitantRepository;

    @Scheduled(
        cron = "0 0 0 1/1 * ?"
    )
    public void updateVisitorData() {
        Set<String> keys = this.redisTemplate.keys("*_*");

        String key;
        for(Iterator var2 = keys.iterator(); var2.hasNext(); this.redisTemplate.delete(key)) {
            key = (String)var2.next();
            String[] parts = key.split("_");
            String userIp = parts[0];
            LocalDate date = LocalDate.parse(parts[1]);
            ValueOperations<String, String> valueOperations = this.redisTemplate.opsForValue();
            String userAgent = (String)valueOperations.get(key);
            if (!this.visitantRepository.existsByUserIpAndVisitDate(userIp, date)) {
                Visitant visitor = Visitant.builder().userIp(userIp).userAgent(userAgent).visitDate(date).build();
                this.visitantRepository.save(visitor);
            }
        }

    }

    public VisitantScheduler(final RedisTemplate<String, String> redisTemplate, final VisitantRepository visitantRepository) {
        this.redisTemplate = redisTemplate;
        this.visitantRepository = visitantRepository;
    }
}