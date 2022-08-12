package com.ssfAssessment.app;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class NewsController {

    Articles service;

    @Autowired
    private NewsService newSvc;

    @GetMapping(path="/news")
    public String showArticles(Model model){
        Articles a = new Articles();
        Optional<Articles> opta = newSvc.getArticles(a);
        if(opta.isEmpty()){
            model.addAttribute("articles", new Articles());
            return "text";
        }
        model.addAttribute("articles", opta.get());
        model.addAttribute("id", a.getId());
        model.addAttribute("title", a.getTitle());
        model.addAttribute("url", a.getUrl());
        model.addAttribute("imageurl", a.getImageurl());
        model.addAttribute("body", a.getBody());
        model.addAttribute("tags", a.getTags());
        model.addAttribute("categories", a.getCategories());
        return "text";
    }
    
    @PostMapping
    public ResponseEntity<Articles> createArticlesSave(@RequestBody Articles asv) {
        int x = service.save(asv);
        if (x > 0)
            asv.setInsertCount(x);
        return ResponseEntity.ok(asv);
    }
}