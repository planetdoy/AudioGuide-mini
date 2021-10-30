package com.miniproject.audioguide.domain.quest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class QuestRepository {
    private final EntityManager em;

    public void save(Quest quest) {
        em.persist(quest);
    }

    public List<Quest> findAll() {
        return em.createQuery(
                "select q from Quest q" +
                        " join fetch q.author a" +
                        " join fetch q.image i", Quest.class)
                .getResultList();
    }
}
