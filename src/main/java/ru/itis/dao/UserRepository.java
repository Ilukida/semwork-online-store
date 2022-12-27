package ru.itis.dao;

import ru.itis.models.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByEmail(String email);
    User save(User user);
    void updateAvatarForUser(Integer userId, Integer fileId);
}
