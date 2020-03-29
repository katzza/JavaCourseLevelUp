package org.levelup.job.list.Jdbc;

import org.levelup.job.list.Domain.Position;

import java.sql.*;
import java.util.Collection;

public class PositionJdbcService implements PositionService {
    @Override
    public Position createPosition(String name) throws SQLException {
        Connection connection = JbdcUtils.getConnection();
        //1. Добавили в базу
        PreparedStatement statement = connection.prepareStatement("insert into positions (name) "+
                "select (?) where not exists (select 1 from positions where name = (?))"); // ? имеет индекс 1 и 2
        statement.setString(1, name); //подготовили, но не послали
        statement.setString(2, name);
        int rowChanged = statement.executeUpdate();  //insert/update/delete  //выполнили запрос
        System.out.println("Количество добавленных строк"+rowChanged);
        //2. Берём данные по вновьсозданной позиции из базы и создаём объект Java
        // Если бы способ не возвращал позишн, можно было бы ограничиться (1)
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet =selectStatement.executeQuery("select * from positions where name = '"+name+"'");  //SELECT
        resultSet.next();
        int id = resultSet.getInt(1);
        String positionName =resultSet.getString("name");
        System.out.println(id+" "+positionName);
        return new Position(id, positionName);
    }

    @Override
    public void deletePositionById(int id) {

    }

    @Override
    public void deletePositionByName(String name) {

    }

    @Override
    public Collection<Position> findAllPositionWhichNameLike(String name) {
        return null;
    }

    @Override
    public Position findPositionById(int id) {
        return null;
    }

    @Override
    public Collection<Position> findAllPositions() {
        return null;
    }

    @Override
    public Position findPositionByName(String name) {
        return null;
    }
}
