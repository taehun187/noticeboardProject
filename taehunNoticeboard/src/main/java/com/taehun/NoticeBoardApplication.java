package com.taehun;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableScheduling
@SpringBootApplication
public class NoticeBoardApplication {
    public NoticeBoardApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(NoticeBoardApplication.class, args);
    }
}
