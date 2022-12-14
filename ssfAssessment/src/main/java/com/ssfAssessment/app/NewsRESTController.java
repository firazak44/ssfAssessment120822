package com.ssfAssessment.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/news", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class NewsRESTController {
    
    //@Autowired
    Articles service;

    // @PostMapping
    // public ResponseEntity<Articles> createArticlesSave(@RequestBody Articles asv) {
    //     int x = service.save(asv);
    //     if (x > 0)
    //         asv.setInsertCount(x);
    //     return ResponseEntity.ok(asv);
    // }

    @GetMapping(path = "/news/{aId}")
    public ResponseEntity<Articles> getArticlesById(@PathVariable String aId) {
        Articles a = service.findById(aId);
        return ResponseEntity.ok(a);
    }

    @PutMapping(path = "/news/{aId}")
    public ResponseEntity<Articles> updateArticles(@RequestBody Articles asv) {
        int aResult = service.update(asv);
        if (aResult > 0)
            asv.setUpdateCount(aResult);
        return ResponseEntity.ok(asv);
    }
}
