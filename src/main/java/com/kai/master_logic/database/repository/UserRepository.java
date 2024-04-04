package com.kai.master_logic.database.repository;

import com.kai.master_logic.database.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void updateUser(User user) {
        if (user != null) {
            users.remove(user);
            users.add(user);
        } else {
            throw new IllegalArgumentException();
        }
    }


    public User getById(Long id) {
        User foundedUser = null;

        for (User user : users) {
            if (user.getId().equals(id)) {
                foundedUser = user;
                break;
            }
        }

        if (foundedUser == null) {
            throw new IllegalArgumentException("incorrect ID send");
        }


        return foundedUser;
    }

    public void deleteById(Long id) {
        users.removeIf(entryId -> entryId.getId().equals(id));
    }

}
