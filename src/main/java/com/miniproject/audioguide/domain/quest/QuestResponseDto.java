package com.miniproject.audioguide.domain.quest;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QuestResponseDto {

    /**
     *
     *  {
     *     key: "1",
     *     questTitle: "Glutes",
     *     author: "Home",
     *     createdTime: "Nov 17th, 2021",
     *     image: "https://~/abc.jpg",
     *   },
     */
    private Long key;
    private String questTitle;
    private String author;
    private LocalDateTime createdTime;
    private String image;

    public QuestResponseDto(Quest quest) {
        this.key = quest.getId();
        this.questTitle = quest.getTitle();
        this.author = quest.getAuthor().getLoginId();
        this.createdTime = LocalDateTime.now();
        this.image = quest.getImage().getPath();
    }
}
