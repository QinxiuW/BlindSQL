package org.exmaple.BlindSql.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;




@Service
public class SqlService {

  @Value("${spring.datasource.url}")
  private String connectionString;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  public ResultSet getResultSet(String sql) throws SQLException {
    Connection conn= getConnection();
    Statement stat = conn.createStatement();
    ResultSet result = stat.executeQuery(sql);
    stat.close();
    conn.close();
    return result;
  }

  public Connection getConnection() throws SQLException {
    return  DriverManager.getConnection(connectionString, username, password);
  }
}
