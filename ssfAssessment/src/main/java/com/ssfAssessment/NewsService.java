package com.ssfAssessment;

import java.util.Optional;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NewsService {
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
            // resp = template.getForEntity(currencyExchangeUrl, String.class);
            Articles as = Articles.createJson(resp.getBody());
            return Optional.of(as);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}

