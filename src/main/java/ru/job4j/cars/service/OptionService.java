package ru.job4j.cars.service;

import ru.job4j.cars.model.*;
import ru.job4j.cars.repository.*;

import java.util.List;

/**
 * Класс является сервисным слоем для работы с объектами - полями Advert
 *
 * @author ikioresko
 * @version 0.2
 */
public class OptionService {
    private final BodyRepo body = new BodyRepo();
    private final BrandRepo brand = new BrandRepo();
    private final CategoryRepo category = new CategoryRepo();
    private final ModelRepo model = new ModelRepo();

    public List<Body> getAllBody() {
        return body.getAllBody();
    }

    public List<Brand> getAllBrand() {
        return brand.getAllBrand();
    }

    public List<Model> getModelByBrandID(int id) {
        return model.getModelByBrandID(id);
    }

    public List<Category> getAllCategory() {
        return category.getAllCategory();
    }

    public Body getBodyByID(int id) {
        return body.getBodyByID(id);
    }

    public Brand getBrandByID(int id) {
        return brand.getBrandByID(id);
    }

    public Model getModelByID(int id) {
        return model.getModelByID(id);
    }

    public Category getCategoryByID(int id) {
        return category.getCategoryByID(id);
    }

}
