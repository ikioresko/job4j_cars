package ru.job4j.cars.repository;

import ru.job4j.cars.model.User;

/**
 * Класс осуществляет взаимодействие объекта User с базой данных посредством Hibernate
 *
 * @author ikioresko
 * @version 0.1
 */
public class UserRepo {
    private final TransactionManager manager = TransactionManager.getInstance();

    public User getAuthorByID(int id) {
        return (User) manager.tx(session ->
                session.createQuery("from User where id = :ID")
                        .setParameter("ID", id).uniqueResult());
    }

    public User getUserByUsername(String username) {
        return (User) manager.tx(session ->
                session.createQuery("from User where username = :name")
                        .setParameter("name", username).uniqueResult());
    }

    public Integer regUser(User user) {
        return (Integer) manager.tx(session -> session.save(user));
    }
}
