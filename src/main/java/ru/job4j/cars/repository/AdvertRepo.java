package ru.job4j.cars.repository;

import ru.job4j.cars.model.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Класс осуществляет взаимодействие объекта Advert с базой данных посредством Hibernate
 *
 * @author ikioresko
 * @version 0.2
 */
public class AdvertRepo {
    private final TransactionManager manager = new TransactionManager().getInstance();

    private Date getLastDay() {
        Date d = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, -24);
        return calendar.getTime();
    }

    public List<Advert> getAdvByLastDay() {
        return manager.tx(session -> session.createQuery("from Advert where created >= :lastDay")
                .setParameter("lastDay", getLastDay()).list());
    }

    public Collection<Advert> getAdvWithPhoto() {
        return manager.tx(session -> session
                .createQuery("from Advert where length(photoPath) > 0").list());
    }

    public Collection<Advert> getAdvByBrand(int id) {
        return manager.tx(session -> session.createQuery("from Advert where brand = :fBrand")
                .setParameter("fBrand", id).list());
    }

    public void createOrUpdateAdvert(Advert advert) {
        manager.tx(session -> session.merge(advert));
    }

    public List<Advert> getAllAdvert() {
        return manager.tx(session -> session.createQuery("from Advert order by id").list());
    }

    public Advert getAdvertByID(int id) {
        return (Advert) manager.tx(session -> session.createQuery("from Advert where id = :id")
                .setParameter("id", id).uniqueResult());
    }
}
