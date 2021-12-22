package ru.job4j.cars.repository;

import ru.job4j.cars.model.Category;

import java.util.List;

/**
 * Класс осуществляет взаимодействие объекта Category с базой данных посредством Hibernate
 *
 * @author ikioresko
 * @version 0.1
 */
public class CategoryRepo {
    private final TransactionManager manager = TransactionManager.getInstance();

    public List<Category> getAllCategory() {
        return manager.tx(session -> session.createQuery("from Category").list());
    }

    public Category getCategoryByID(int id) {
        return (Category) manager.tx(session -> session.createQuery("from Category where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }

}
