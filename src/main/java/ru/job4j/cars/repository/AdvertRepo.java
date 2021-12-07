package ru.job4j.cars.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.model.*;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Класс осуществляет взаимодействие с базой данных посредством Hibernate
 *
 * @author ikioresko
 * @version 0.1
 */
public class AdvertRepo {
    private static final Logger LOG = LoggerFactory.getLogger(AdvertRepo.class.getName());
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

    public List<Advert> getAdvByLastDay() {
        return this.tx(session -> session.createQuery("from Advert where created >= :lastDay")
                .setParameter("lastDay", getLastDay()).list());
    }

    public Collection<Advert> getAdvWithPhoto() {
        return this.tx(session -> session
                .createQuery("from Advert where length(photoPath) > 0").list());
    }

    public Collection<Advert> getAdvByBrand(int id) {
        return this.tx(session -> session.createQuery("from Advert where brand = :fBrand")
                .setParameter("fBrand", id).list());
    }

    public void createOrUpdateAdvert(Advert advert) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(advert);
            session.getTransaction().commit();
        } catch (Exception e) {
            LOG.error("Exception: ", e);
        }
    }

    public List<Advert> getAllAdvert() {
        return this.tx(session -> session.createQuery("from Advert order by id").list());
    }

    public Advert getAdvertByID(int id) {
        return (Advert) this.tx(session -> session.createQuery("from Advert where id = :id")
                .setParameter("id", id).uniqueResult());
    }

    public List<Body> getAllBody() {
        return this.tx(session -> session.createQuery("from Body").list());
    }

    public List<Brand> getAllBrand() {
        return this.tx(session -> session.createQuery(
                "select distinct b from Brand b join fetch b.model m"
                        + " where b.id = m.brand").list());
    }

    public List<Model> getModelByBrandID(int id) {
        return this.tx(session -> session.createQuery("from Model where brand = :Brand")
                .setParameter("Brand", id).list());
    }

    public List<Category> getAllCategory() {
        return this.tx(session -> session.createQuery("from Category").list());
    }

    public Body getBodyByID(int id) {
        return (Body) this.tx(session -> session.createQuery("from Body where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }

    public Brand getBrandByID(int id) {
        return (Brand) this.tx(session -> session.createQuery("from Brand where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }

    public Model getModelByID(int id) {
        return (Model) this.tx(session -> session.createQuery("from Model where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }

    public Category getCategoryByID(int id) {
        return (Category) this.tx(session -> session.createQuery("from Category where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }

    public User getAuthorByID(int id) {
        return (User) this.tx(session -> session.createQuery("from User where id = :ID")
                .setParameter("ID", id).uniqueResult());
    }

    public User getUserByUsername(String username) {
        return (User) this.tx(session -> session.createQuery("from User where username = :name")
                .setParameter("name", username).uniqueResult());
    }

    public Integer regUser(User user) {
        return (Integer) this.tx(session -> session.save(user));
    }

    public static void main(String[] args) {
        List<Model> m = new AdvertRepo().getModelByBrandID(1);
        System.out.println(m);
    }
}
