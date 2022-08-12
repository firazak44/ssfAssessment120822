package com.ssfAssessment.app;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NewsService implements ArticlesRepo{
    private static String URL = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

    public Optional<Articles>getArticles(Articles a){
        String apiKey = System.getenv("api_key");
        String newsArticleUrl = UriComponentsBuilder.fromUriString(URL)
        .queryParam("id", a.getId())
        .queryParam("title", a.getTitle())
        .queryParam("url", a.getUrl())
        .queryParam("imageurl", a.getImageurl())
        .queryParam("body", a.getBody())
        .queryParam("tags", a.getTags())
        .queryParam("categories", a.getCategories())    
        .toUriString();

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;
        
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.set("apikey", apiKey);
            HttpEntity request = new HttpEntity(headers);
            resp = template.exchange(
                newsArticleUrl, 
                HttpMethod.GET, request, 
                String.class,
                1);
            Articles as = Articles.createJson(resp.getBody());
            return Optional.of(as);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
 
    @Autowired
    @Qualifier("feeds")
    RedisTemplate<String, Articles> redisTemplate;
    @Override
    public int saveArticles(Articles atc) {
        redisTemplate.opsForValue().set(atc.getId(), atc);
        Articles result = (Articles) redisTemplate.opsForValue().get(atc.getId());
        if (result != null)
            return 1;
        return 0;
    }

    @Override
    public Articles findById(String aId) {
        Articles result = (Articles) redisTemplate.opsForValue().get(aId);
        return result;
    }

    @Override
    public int updatessfAssessment(Articles atc) {
        Articles result = (Articles) redisTemplate.opsForValue().get(atc.getId());
        if (result != null)
            return 1;
        return 0;
    }

    public Set<String> searchKeys(String index) {
        String pattern = "*%s*".formatted(index);
        return redisTemplate.keys(pattern);
    }

}

