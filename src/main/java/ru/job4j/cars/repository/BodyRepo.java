package ru.job4j.cars.repository;

import ru.job4j.cars.model.Body;

import java.util.List;

/**
 * Класс осуществляет взаимодействие объекта Body с базой данных посредством Hibernate
 *
 * @author ikioresko
 * @version 0.1
 */
public class BodyRepo {
    private final TransactionManager manager = new TransactionManager().getInstance();

    public List<Body> getAllBody() {
        return manager.tx(session -> session.createQuery("from Body").list());
    }

    public Body getBodyByID(int id) {
        return (Body) manager.tx(session -> session.createQuery("from Body where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }
}
