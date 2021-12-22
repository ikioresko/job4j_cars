package ru.job4j.cars.repository;

import ru.job4j.cars.model.Brand;

import java.util.List;

/**
 * Класс осуществляет взаимодействие объекта Brand с базой данных посредством Hibernate
 *
 * @author ikioresko
 * @version 0.1
 */
public class BrandRepo {
    private final TransactionManager manager = TransactionManager.getInstance();

    public List<Brand> getAllBrand() {
        return manager.tx(session -> session.createQuery(
                "select distinct b from Brand b join fetch b.model m"
                        + " where b.id = m.brand").list());
    }

    public Brand getBrandByID(int id) {
        return (Brand) manager.tx(session -> session.createQuery("from Brand where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }
}
