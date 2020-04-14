package application.dao;

import hibernate.domain.User;

import java.util.Collection;

public interface UserDao {
   User createUser (String name, String lastName, String passport, Collection <String> addresses);
}
