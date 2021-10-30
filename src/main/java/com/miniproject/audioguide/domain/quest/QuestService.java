package com.miniproject.audioguide.domain.quest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestService {
    private final QuestRepository questRepository;

    @Transactional
    public void save(Quest quest) {
        questRepository.save(quest);
    }

    public List<Quest> findAll() {
        List<Quest> list = questRepository.findAll();
        if (list.isEmpty()) {
            return null;
        }
        return list;
    }
}
