package org.exmaple.BlindSql.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import org.exmaple.BlindSql.model.News;
import org.exmaple.BlindSql.service.NewsService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/")
public class NewsController {

  private final NewsService newsService;

  public NewsController(NewsService newsService) {
    this.newsService = newsService;
  }

  @GetMapping("/news")
  public String getNewsById(Model model, @RequestParam String id){
    News news = newsService.getById(id);
    model.addAttribute("news",news);
    return "news";
  }

}
