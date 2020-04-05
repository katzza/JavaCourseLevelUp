package org.levelup.job.list.Jdbc;

import org.levelup.job.list.Domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class UserJdbcService implements UserService {
    @Override
    public User createUser(String name, String lastName, String passport) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            User us = this.findByPassport(passport);
            if (us != null) {
                return us;
            }
            PreparedStatement statement = connection.prepareStatement("insert into users (name, last_name, passport) " +
                    "values ((?),(?),(?))"); //
            statement.setString(1, name); //подготовили, но не послали
            statement.setString(2, lastName);
            statement.setString(3, passport);
            int rowChanged = statement.executeUpdate();  //insert/update/delete  //выполнили запрос
            System.out.println("Количество добавленных строк" + rowChanged);
            //2. Берём данные по вновьсозданной позиции из базы и создаём объект Java
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from users where passport = '" + passport + "'");  //SELECT
            resultSet.next();
            int id = resultSet.getInt(1);
            return new User(id, name, lastName, passport);
        }
    }

    @Override
    public User createUser(String passport) {
        return null;
    }

    @Override
    public User findByPassport(String passport) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where passport = ?");
            statement.setString(1, passport);
            ResultSet resultSet = statement.executeQuery();
            System.out.println( resultSet.next());
            String lastName = resultSet.getString("last_name");
            String name = resultSet.getString("name");
            int id = resultSet.getInt("id");
            System.out.println("KKKK" + id +" " + name +"   "+ lastName + "   " + passport);
            return new User(id, name, lastName, passport);
        }
    }

    @Override
    public Collection<User> findByNameAndLastName(String name, String lastName) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where name = (?) and last_name = (?)");
            statement.setString(1, name);
            statement.setString(2, lastName);
            ResultSet resultSet = statement.executeQuery();
            return extractPositions(resultSet);
        }
    }

    @Override
    public Collection<User> findByLastName(String lastName) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select * from users where last_name = (?)");
            statement.setString(1, lastName);
            ResultSet resultSet = statement.executeQuery();
            return extractPositions(resultSet);
        }
    }

    @Override
    public void deleteUserByPassport(String passport) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("delete from users where passport = (?)"); ////todo удалить из связанной таблицы
            statement.setString(1, passport);
            int rowDeleted = statement.executeUpdate();
            System.out.println("deleted" + rowDeleted);
        }
    }

    @Override
    public User updateUser(String passport, String name, String lastName) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE users set name =(?),  last_name = (?) WHERE passport = (?)");
            statement.setString(1, name);
            statement.setString(1, lastName);
            statement.setString(1, passport);
            int rowUpdated = statement.executeUpdate();
            System.out.println("updated" + rowUpdated);
//return User
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from users where passport = '" + passport + "'");
            resultSet.next();
            int id = resultSet.getInt(1);
            return new User(id, name, lastName, passport);
        }
    }

    private Collection<User> extractPositions(ResultSet resultSet) throws SQLException {
        Collection<User> users = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String lastName = resultSet.getString("last_name");
            String passport = resultSet.getString("passport");
            users.add(new User(id, name, lastName, passport));
        }
        return users;
    }
}
