package org.exmaple.BlindSql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * BlindSqlApplication.
 *
 * @Description BlindSqlApplication
 * @Date 22/12/22 17:55
 * @Created by qinxiuwang
 */
@SpringBootApplication
public class BlindSqlApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(BlindSqlApplication.class, args);
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(BlindSqlApplication.class);
  }
}
