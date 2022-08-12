package com.ssfAssessment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path="/text")
public class NewsController {

    @Autowired
    private NewsService newSvc;

    @GetMapping
    public String exchange(@RequestParam(required=true)String id, 
        @RequestParam(required=true)String title, 
        @RequestParam(required=true)String url,
        @RequestParam(required=true)String imageurl, 
        @RequestParam(required=true)String body, 
        @RequestParam(required=true)String tags, 
        @RequestParam(required=true)String categories, Model model){
        Articles a = new Articles();
        a.setId(id);
        a.setTitle(title);
        a.setUrl(url);
        a.setImageurl(imageurl);
        a.setBody(body);
        a.setTags(tags);
        a.setCategories(categories);
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
}