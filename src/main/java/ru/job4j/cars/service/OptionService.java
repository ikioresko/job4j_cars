package ru.job4j.cars.service;

import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.AdvertRepo;

import java.util.List;

/**
 * Класс является сервисным слоем для работы с объектами - полями Advert
 *
 * @author ikioresko
 * @version 0.1
 */
public class OptionService {
    private final AdvertRepo repo = new AdvertRepo();

    public List<Body> getAllBody() {
        return repo.getAllBody();
    }

    public List<Brand> getAllBrand() {
        return repo.getAllBrand();
    }

    public List<Model> getModelByBrandID(int id) {
        return repo.getModelByBrandID(id);
    }

    public List<Category> getAllCategory() {
        return repo.getAllCategory();
    }

    public Body getBodyByID(int id) {
        return repo.getBodyByID(id);
    }

    public Brand getBrandByID(int id) {
        return repo.getBrandByID(id);
    }

    public Model getModelByID(int id) {
        return repo.getModelByID(id);
    }

    public Category getCategoryByID(int id) {
        return repo.getCategoryByID(id);
    }

}
