package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс реализует объект - тип кузова
 *
 * @author ikioresko
 * @version 0.1
 */
@Entity
public class Body {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    public Body() {
    }

    public Body(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameType) {
        this.name = nameType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Body body = (Body) o;
        return id == body.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BodyType{"
                + "id=" + id
                + ", nameType='" + name + '\''
                + '}';
    }
}