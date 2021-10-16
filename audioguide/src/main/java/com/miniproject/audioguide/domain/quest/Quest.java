package com.miniproject.audioguide.domain.quest;

import com.miniproject.audioguide.domain.file.File;
import com.miniproject.audioguide.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quest {

    @Id
    @GeneratedValue
    @Column(name = "quest_id")
    private Long id;

    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    private Member author;

    @OneToOne(fetch = FetchType.LAZY)
    private File image;

    @OneToOne(fetch = FetchType.LAZY)
    private File audio;

}



