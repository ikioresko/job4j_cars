package ru.job4j.cars.repository;

import ru.job4j.cars.model.Model;

import java.util.List;

/**
 * Класс осуществляет взаимодействие объекта Model с базой данных посредством Hibernate
 *
 * @author ikioresko
 * @version 0.1
 */
public class ModelRepo {
    private final TransactionManager manager = new TransactionManager().getInstance();

    public Model getModelByID(int id) {
        return (Model) manager.tx(session -> session.createQuery("from Model where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }

    public List<Model> getModelByBrandID(int id) {
        return manager.tx(session -> session.createQuery("from Model where brand = :Brand")
                .setParameter("Brand", id).list());
    }
}
