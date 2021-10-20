package com.miniproject.audioguide.domain.memberQuest;

import com.miniproject.audioguide.domain.quest.Quest;
import com.miniproject.audioguide.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MemberQuest {

    @Id
    @GeneratedValue
    @Column(name = "member_quest_id")
    private Long id;

    private LocalDateTime createdTime;
    private LocalDateTime endTime;
    private QuestStatus questStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quest_id")
    private Quest quests;

    enum QuestStatus{
        YET,COMP
    }
}
