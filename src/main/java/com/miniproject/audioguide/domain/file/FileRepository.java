package com.miniproject.audioguide.domain.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class FileRepository {
    private final EntityManager em;

    public void save(File file) {
        em.persist(file);
    }
}
