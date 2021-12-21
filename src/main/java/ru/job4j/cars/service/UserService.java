package ru.job4j.cars.service;

import ru.job4j.cars.model.User;
import ru.job4j.cars.repository.UserRepo;

/**
 * Класс является сервисным слоем для работы с объектами User
 *
 * @author ikioresko
 * @version 0.2
 */
public class UserService {
    private final UserRepo repo = new UserRepo();

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
