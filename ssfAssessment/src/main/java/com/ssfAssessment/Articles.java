package com.ssfAssessment;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.security.Timestamp;
import java.util.Random;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Articles implements Serializable{
    private String title;
    private String categories;
    private String body;
    private String tags;
    private String url;
    private String imageurl;
    private String id;
    private Timestamp published_on;
    
    public Articles() {
        this.id = generateId(8);
    }
    private synchronized String generateId(int numchars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while (strBuilder.length() < numchars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder.toString().substring(0, numchars);
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCategories() {
        return categories;
    }
    public void setCategories(String categories) {
        this.categories = categories;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getImageurl() {
        return imageurl;
    }
    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Timestamp getPublished_on() {
        return published_on;
    }
    public void setPublished_on(Timestamp published_on) {
        this.published_on = published_on;
    }

    public static Articles createJson(String json) throws IOException{
        Articles a = new Articles();
        try(InputStream is = new ByteArrayInputStream(json.getBytes())){
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            a.id = o.getJsonString("id").getString();
            a.title = o.getJsonString("title").getString();
            a.body = o.getJsonString("body").getString();
            a.categories = o.getJsonString("categories").getString();
            a.tags = o.getJsonString("tags").getString();
            a.url = o.getJsonString("url").getString();
            a.imageurl = o.getJsonString("imageurl").getString();
        }
        return a;
    }


    //from NewsRESTController
    public int save(Articles ms) {
        return 0;
    }
    public void setInsertCount(int x) {
    }
    public Articles findById(String aId) {
        return null;
    }
    public int update(Articles asv) {
        return 0;
    }
    public void setUpdateCount(int aResult) {
    }

    
}
