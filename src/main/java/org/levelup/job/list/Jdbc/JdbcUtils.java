package org.levelup.job.list.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
    public static Connection getConnection () throws SQLException { //подключение к нашей бд
        //url -
        //jdbc: <vendor name>://<host(IP address)>:<port>/<db name>
        //localhost - наша машина
        Connection connection = DriverManager.getConnection
                ("jdbc:postgresql://localhost:5432/jobs", "postgres", "root");
        return connection;
    }
}


