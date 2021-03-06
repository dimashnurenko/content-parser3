package com.huk.services.dao;

import com.huk.entities.SongStatisticEntity;
import com.huk.exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.function.Function;

@Component
public class SongStatisticDao {
    private final SessionFactory sessionFactory;

    public SongStatisticDao(EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    public SongStatisticEntity getById(Long id) {
        return perform(session ->
            session.get(SongStatisticEntity.class, id), "Statistic not found");
    }

    public SongStatisticEntity create(SongStatisticEntity entity) {
        return perform(session -> {
            session.persist(entity);
            return entity;
        }, "Cannot store lyrics statistics to database");
    }

    private <T> T perform(Function<Session, T> dbFunc, String message) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                T result = dbFunc.apply(session);
                session.getTransaction().commit();
                return result;
            } catch (Exception e) {
                session.getTransaction().rollback();
                throw new DaoException(message, e);
            }
        }
    }

    public List<SongStatisticEntity> createAll(List<SongStatisticEntity> entities) {
        for (SongStatisticEntity entity : entities) {
            create(entity);
        }
        return entities;
    }

//    private SongStatisticEntity instanceToEntity(SongStatisticEntity instance) {
//        SongStatisticEntity entity = new SongStatisticEntity();
//        entity.setDate(instance.getDate());
//        entity.setContentUrl(instance.getContentUrl());
//        entity.setContent(instance.getContent());
//        entity.setDate(instance.getDate());
//        entity.setAmountSameWords(instance.getAmountSameWords());
//        entity.setTotalWordsAmount(instance.getTotalWordsAmount());
//        entity.setMostPopularWords(instance.getMostPopularWords());
//        entity.setAmountUniqueWords(instance.getAmountUniqueWords());
//        return entity;
//    }
//
}
