package org.exmaple.BlindSql.service;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.exmaple.BlindSql.model.News;
import org.springframework.stereotype.Service;

@Service
public class NewsService {

  private final SqlService sqlService;

  public NewsService(SqlService sqlService) {
    this.sqlService = sqlService;
  }

  public News getById(String id){
    String sql = "SELECT * FROM uoc.news ns WHERE ns.id = ?";
    News news = new News();
    try {
      PreparedStatement stmt = sqlService.getConnection().prepareStatement(sql);
      stmt.setString(1, id);
      ResultSet result = stmt.executeQuery();

      if (result.next()){
        int currentID = Integer.parseInt(result.getString("id"));
        String title= result.getString("title");
        String body = result.getString("body");
        SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date datetime = dateFormat.parse(result.getString("date_time"));
        news.setId(currentID);
        news.setTitle(title);
        news.setBody(body);
        news.setDatetime(datetime);
      }
    } catch (SQLException | ParseException e) {
      throw new RuntimeException("Sql query exception");
    }
    return news;
  }
}
