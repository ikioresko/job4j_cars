package ru.job4j.cars.filter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.model.Advert;

import java.util.*;
import java.util.function.Function;

public class AdRepository {
    private static final Logger LOG = LoggerFactory.getLogger(AdRepository.class.getName());
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
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

    private Date getLastDay() {
        Date d = new Date(System.currentTimeMillis());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, -24);
        return calendar.getTime();
    }

    public Collection<Advert> advByLastDay() {
        return this.tx(session -> session.createQuery("from Advert where created >= :lastDay")
                .setParameter("lastDay", getLastDay()).list());
    }

    public Collection<Advert> advWithPhoto() {
        return this.tx(session -> session
                .createQuery("from Advert where length(photoPath) > 0").list());
    }

    public Collection<Advert> advByBrand(String brand) {
        return this.tx(session -> session.createQuery("from Advert where carBrand = :fBrand")
                .setParameter("fBrand", brand).list());
    }
}
