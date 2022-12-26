package org.exmaple.BlindSql.controller;

import org.exmaple.BlindSql.model.News;
import org.exmaple.BlindSql.service.NewsService;
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
  public String getNewsById(Model model, @RequestParam Integer id){
    News news = newsService.getById(id);
    model.addAttribute("news",news);
    return "news";
  }
}
