package org.exmaple.BlindSql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController.
 *
 * @Description TODO
 * @Date 22/12/22 19:24
 * @Created by qinxiuwang
 */
@RestController
public class TestController {

  @GetMapping("/check")
  public String check() {
    return "ok";
  }


  @GetMapping("/db")
  public List<String> db() throws SQLException {
    return getResultFromDB();
  }

  private List<String> getResultFromDB() throws SQLException {

    List<String> result = new ArrayList<>();
    ResultSet rs = getResultSet("SELECT * FROM INFORMATION_SCHEMA.TABLES");
    while (rs.next()) {
      result.add(rs.getString(1));
    }
    return result;
  }

  private ResultSet getResultSet(String sql) throws SQLException {

    Connection conn = DriverManager.getConnection("jdbc:h2:mem:Prod", "root", "test");
    Statement stat = conn.createStatement();
    return stat.executeQuery(sql);
  }
}
