package ru.job4j.cars.service;

import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.AdvertRepo;

/**
 * Класс является сервисным слоем для работы с объектами User
 *
 * @author ikioresko
 * @version 0.1
 */
public class UserService {
    private final AdvertRepo repo = new AdvertRepo();

    public User getUserByUsername(String name) {
        return repo.getUserByUsername(name);
    }

    public void regUser(User user) {
        repo.regUser(user);
    }

    public User getAuthorByID(int id) {
        return repo.getAuthorByID(id);
    }
}
