package org.exmaple.BlindSql.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.exmaple.BlindSql.model.News;
import org.exmaple.BlindSql.service.SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

//  @Autowired
//  NewsService newsService;

  @Autowired
  SqlService  sqlService;


  @GetMapping("/dataSource")
  public News dataSource(@RequestParam String id) throws SQLException {
    String sql = "SELECT * FROM news ns WHERE ns.id ="+ id;
    try {
      ResultSet result = sqlService.getResultSet(sql);
      if (result.next()){
        int currentID = Integer.parseInt(result.getString("id"));
        String title= result.getString("title");
        String body = result.getString("body");
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = dateFormat.parse(result.getString("date_time"));
        return News.builder()
            .id(currentID)
            .title(title)
            .body(body)
            .datetime(datetime)
            .build();
      }
    } catch (SQLException | ParseException e) {
      throw new RuntimeException("Sql query exception");
    }
    return News.builder().build();
  }

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
    ResultSet rs = getResultSet("SELECT * FROM users");
    while (rs.next()) {
      result.add(rs.getString(1));
    }
    return result;
  }

  private ResultSet getResultSet(String sql) throws SQLException {

    Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/uoc", "root", "admin123");
    Statement stat = conn.createStatement();
    return stat.executeQuery(sql);
  }
}
