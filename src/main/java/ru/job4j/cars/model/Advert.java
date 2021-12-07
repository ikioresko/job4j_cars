package ru.job4j.cars.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Класс реализует объект - объявление
 *
 * @author ikioresko
 * @version 0.1
 */
@Entity
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "brand_id")
    private Brand brand;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "model_id")
    private Model model;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "body_id")
    private Body body;
    private String description;
    private int price;
    private boolean sold;
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "author_id")
    private User author;
    private String photoPath;

    public Advert() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Advert advert = (Advert) o;
        return id == advert.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Advert{"
                + "id=" + id
                + ", created=" + created
                + ", category=" + category
                + ", brand=" + brand
                + ", model=" + model
                + ", body=" + body
                + ", description='" + description + '\''
                + ", price=" + price
                + ", sold=" + sold
                + ", author=" + author
                + ", photoPath='" + photoPath + '\''
                + '}';
    }
}