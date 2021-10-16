package com.miniproject.audioguide.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findByLoginId(String loginId) {

        List<Member> members = memberRepository.findAll();

        if (members.isEmpty()) {
            return null;
        }
        for (Member member : members) {
            if (loginId.equals(member.getLoginId())) {
                return member;
            }
        }
        return null;
    }
}
