package application.dao;

import application.domain.UserEntity;

import java.util.Collection;

public interface UserDao {
   UserEntity createUser (String name, String lastName, String passport, Collection <String> addresses);
}
