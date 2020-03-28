package org.levelup.job.list.Jdbc;

import org.levelup.job.list.Domain.Position;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class JobListService {
    //create - return smth
    //save - return void

    public Position createPosition(String name) throws SQLException {   //!открыть коннекшн - закрыть коннекшн
        Connection connection = JbdcUtils.getConnection();
        //Statement интерфейсs для CRUD
            // PreparedStatement избегать скль инъекций
            // CallableStatement -запускать функции и процедуры на sql
        PreparedStatement statement = connection.prepareStatement("insert into positions (name) values (?)"); // ? имеет индекс 1
        statement.setString(1, name); //подготовили, но не послали
        int rowChanged = statement.executeUpdate();  //insert/update/delete
        System.out.println("Количество добавленных строк"+rowChanged);
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet =selectStatement.executeQuery("select * from positions where name = '"+name+"'");  //SELECT
        // - iterator
        resultSet.next();

            int id = resultSet.getInt(1);
            String positionName =resultSet.getString("name");
            System.out.println(id+" "+positionName);
        return new Position(id, positionName);
    }

    public void deletePosition (String name) throws SQLException {
       // try with resource - close with autoclosable
try (Connection connection = JbdcUtils.getConnection()) {
    PreparedStatement statement = connection.prepareStatement("delete from positions where name = ?");
    statement.setString(1,name); //предотвращение скль инъекций
    int rowDeleted = statement.executeUpdate();
    System.out.println("deleted"+rowDeleted);
} //connection.Close();


    }

    public Collection <Position> findAll() throws SQLException{
        try (Connection connection = JbdcUtils.getConnection()){
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from positions");
            return extractPositions(resultSet);
        }

    }

    public Collection<Position> findPositionWithNameLike(String name) throws SQLException {
        try (Connection connection = JbdcUtils.getConnection()){
            PreparedStatement statement =connection.prepareStatement("select * from positions where name like ?");
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            return extractPositions(resultSet);
        }
    }


    private Collection<Position> extractPositions(ResultSet resultSet) throws SQLException {
        Collection <Position> positions = new ArrayList<>();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            positions.add(new Position(id, name));
        }
        return positions;
    }

    ;


}
