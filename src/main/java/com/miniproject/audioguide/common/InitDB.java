package com.miniproject.audioguide.common;

import com.miniproject.audioguide.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.UUID;

/**
 * 총 주문 2개
 * userA
 *  JPA1 BOOK
 *  JPA2 BOOK
 * userB
 *  SPRING1 BOOK
 *  SPRING2 BOOK
 */
@Component
@RequiredArgsConstructor
public class InitDB {
    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {

        private final EntityManager em;

        public void dbInit1() {
            String loginId = "admin";
            String uuid = UUID.randomUUID().toString();

            String requestPassword = "1234";
            String cryptPassword = Sha256.getSHA256(requestPassword + uuid);

            String password = cryptPassword;
            String name = "master";
            String email = "doydoit@gmail.com";

            Member member = new Member(loginId, password, uuid, name, email);
            em.persist(member);

        }
    }
}
