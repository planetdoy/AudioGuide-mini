package com.miniproject.audioguide.domain.quest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestService {
    private final QuestRepository questRepository;

    @Transactional
    public void save(Quest quest) {
        questRepository.save(quest);
    }
}
