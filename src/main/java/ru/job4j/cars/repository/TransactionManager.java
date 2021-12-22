package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * Класс содержит в себе фабрику сессий и выполняет транзакцию в базе данных посредством Hibernate
 *
 * @author ikioresko
 * @version 0.1
 */
public class TransactionManager {
    private static final TransactionManager INSTANCE = new TransactionManager();
    private static final Logger LOG = LoggerFactory.getLogger(AdvertRepo.class.getName());
    private static final StandardServiceRegistry REGISTRY = new StandardServiceRegistryBuilder()
            .configure().build();
    private static final SessionFactory SF = new MetadataSources(REGISTRY)
            .buildMetadata().buildSessionFactory();

    protected <T> T tx(final Function<Session, T> command) {
        final Session session = SF.openSession();
        final Transaction tx = session.beginTransaction();
        T rsl = null;
        try {
            rsl = command.apply(session);
            tx.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error("Exception: ", e);
        } finally {
            session.close();
        }
        return rsl;
    }

    protected static TransactionManager getInstance() {
        return INSTANCE;
    }
}
