import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * test.
 *
 * @Description TODO
 * @Date 22/12/22 18:48
 * @Created by qinxiuwang
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class test {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @Test
  public void testing() throws SQLException {

    getSessionIdsFromDatabase();
  }


  private List<String> getSessionIdsFromDatabase() throws SQLException {

    List<String> result = new ArrayList<>();
    ResultSet rs = getResultSet("SELECT * FROM SPRING_SESSION");

    while (rs.next()) {
      result.add(rs.getString("SESSION_ID"));
    }
    return result;
  }

  private ResultSet getResultSet(String sql) throws SQLException {

    Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
    Statement stat = conn.createStatement();
    return stat.executeQuery(sql);
  }
}


