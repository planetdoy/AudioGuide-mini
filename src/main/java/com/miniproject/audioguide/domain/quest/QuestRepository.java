package com.miniproject.audioguide.domain.quest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class QuestRepository {
    private final EntityManager em;

    public void save(Quest quest) {
        em.persist(quest);
    }
}
