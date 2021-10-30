package com.miniproject.audioguide.domain.quest;

import com.miniproject.audioguide.common.S3Uploader;
import com.miniproject.audioguide.domain.Result;
import com.miniproject.audioguide.domain.file.File;
import com.miniproject.audioguide.domain.file.FileService;
import com.miniproject.audioguide.domain.member.Member;
import com.miniproject.audioguide.domain.member.MemberService;
import com.miniproject.audioguide.domain.member.SessionConst;
import com.miniproject.audioguide.exception.NoSessionException;
import com.miniproject.audioguide.exception.ValueEmptyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class QuestController {

    private final QuestService questService;
    private final FileService fileService;
    private final MemberService memberService;
    private final S3Uploader s3Uploader;

    /**
     * 퀘스트 등록
     */
    @PostMapping("/api/quests")
    public ResponseEntity<Object> save(@RequestParam("title") String title, @RequestParam("files") MultipartFile multipartFile, HttpServletRequest request) throws IOException {

        String imgUrl = s3Uploader.upload(multipartFile, "static");
        // Domain File Class
        File imgFile = new File(multipartFile, imgUrl);
        fileService.save(imgFile);

        String loginId = getLoginIdBySession(request);
        Member author = memberService.findByLoginId(loginId);

        Quest quest = new Quest(title, author, imgFile);
        questService.save(quest);

        QuestResponseDto questResponseDto = new QuestResponseDto(quest);

        return new ResponseEntity<>(questResponseDto,HttpStatus.CREATED);
    }

    @GetMapping("/api/quests")
    public ResponseEntity<Object> quests() {
        List<Quest> list = questService.findAll();
        if (list == null) throw new ValueEmptyException("퀘스트가 없습니다.");
        List<QuestResponseDto> dtoList = changeEntityToDto(list);

        Result result = new Result(dtoList);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    private List<QuestResponseDto> changeEntityToDto(List<Quest> list) {
        List<QuestResponseDto> responseDtoList = new ArrayList<>();
        for (Quest quest : list) {
            QuestResponseDto dto = new QuestResponseDto(quest);
            responseDtoList.add(dto);
        }
        return responseDtoList;
    }

    private String getLoginIdBySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new NoSessionException("Have No Session, Login First");
        }
        String loginId = (String) session.getAttribute(SessionConst.Login_Member);
        return loginId;
    }
}
