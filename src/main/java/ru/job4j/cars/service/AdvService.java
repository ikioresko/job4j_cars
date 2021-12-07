package ru.job4j.cars.service;

import ru.job4j.cars.model.Advert;
import ru.job4j.cars.repository.AdvertRepo;

import java.util.List;

/**
 * Класс является сервисным слоем для работы с объектами Advert
 *
 * @author ikioresko
 * @version 0.1
 */
public class AdvService {
    private final AdvertRepo repo = new AdvertRepo();

    public List<Advert> getAllAdvert() {
        return repo.getAllAdvert();
    }

    public Advert findAdvertByID(int id) {
        return repo.getAdvertByID(id);
    }

    public void save(Advert advert) {
        repo.createOrUpdateAdvert(advert);
    }

    public List<Advert> getAdvByLastDay() {
        return repo.getAdvByLastDay();
    }

}
