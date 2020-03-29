package org.levelup.job.list.Jdbc;

import org.levelup.job.list.Domain.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PositionJdbcService implements PositionService {
    @Override
    public Position createPosition(String name) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection();) {
            //1. Добавили в базу
            PreparedStatement statement = connection.prepareStatement("insert into positions (name) " +
                    "select (?) where not exists (select 1 from positions where name = (?))"); // ? имеет индекс 1 и 2
            statement.setString(1, name); //подготовили, но не послали
            statement.setString(2, name);
            int rowChanged = statement.executeUpdate();  //insert/update/delete  //выполнили запрос
            System.out.println("Количество добавленных строк" + rowChanged);
            //2. Берём данные по вновьсозданной позиции из базы и создаём объект Java
            // Если бы способ не возвращал позишн, можно было бы ограничиться (1)
            Statement selectStatement = connection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery("select * from positions where name = '" + name + "'");  //SELECT
            resultSet.next();
            int id = resultSet.getInt(1);
            String positionName = resultSet.getString("name");
            System.out.println(id + " " + positionName);
            return new Position(id, positionName);
        }
    }

    @Override
    public void deletePositionById(int id) throws SQLException { //без каскадного удаления
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("delete from positions where id = ?");
            statement.setInt(1, id); //предотвращение скль инъекций
            int rowDeleted = statement.executeUpdate();
            System.out.println("deleted" + rowDeleted);
        }
    }

    @Override
    public void deletePositionByName(String name) throws SQLException {  //без каскадного удаления
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("delete from positions where name = ?");
            statement.setString(1, name); //предотвращение скль инъекций
            int rowDeleted = statement.executeUpdate();
            System.out.println("deleted" + rowDeleted);
        }
    }

    @Override
    public Collection<Position> findAllPositionWhichNameLike(String name) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("select * from positions where upper(name) like upper(?)");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return extractPositions(resultSet);
        }
    }

    private Collection<Position> extractPositions(ResultSet resultSet) throws SQLException {
        Collection<Position> positions = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            positions.add(new Position(id, name));
        }
        return positions;
    }

    @Override
    public Position findPositionById(int id) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("select * from positions where id = (?)");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next(); //сначала мы находимся на заголовках (индекс = -1), через next() заходим на 1ю строчку
            String positionName = resultSet.getString("name");
            System.out.println(id + " " + positionName);
            return new Position(id, positionName);
        }
    }

    @Override
    public Collection<Position> findAllPositions() throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from positions");
            return extractPositions(resultSet);
        }
    }

    @Override
    public Collection<Position> findPositionByName(String name) throws SQLException {
        try (Connection connection = JdbcUtils.getConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("select * from positions where name = (?)");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return extractPositions(resultSet);
        }
    }
}
