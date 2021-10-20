package com.miniproject.audioguide.domain.member;

import com.miniproject.audioguide.common.Sha256;
import com.miniproject.audioguide.exception.NoSessionException;
import com.miniproject.audioguide.exception.NotMatchException;
import com.miniproject.audioguide.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 로그인 api
     */
    @PostMapping("/api/members/login")
    public ResponseEntity<Object> login(@RequestBody LoginForm form, HttpServletRequest request) {
        //아이디 확인
        Member member = checkLoginId(form);
        //비밀번호 확인
        checkPassword(form, member);
        //세션 값 할당
        setSessionToClient(request, member);

        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    private void setSessionToClient(HttpServletRequest request, Member member) {
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.Login_Member, member.getLoginId());
        session.setMaxInactiveInterval(600);
    }

    private Member checkLoginId(LoginForm form) {
        Member member = memberService.findByLoginId(form.getLoginId());
        log.info("member null check = {}", member);
        if (member == null) {
            throw new UserNotFoundException(String.format("ID [%s] not found", form.getLoginId()));
        }
        return member;
    }

    private void checkPassword(LoginForm form, Member member) {
        String cryptPassword = Sha256.getSHA256(form.getPassword() + member.getUuid());
        if (!cryptPassword.equals(member.getPassword())) {
            throw new NotMatchException("Fail to login, Check your ID or PASSWORD");
        }
    }

    /**
     * 로그아웃 api
     * @return
     */
    @GetMapping("/api/members/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new NoSessionException("Have No Session, Login First");
        }
        String loginId = (String) session.getAttribute(SessionConst.Login_Member);

        session.invalidate();
        return new ResponseEntity(String.format("%s logout",loginId),HttpStatus.OK);
    }

}
