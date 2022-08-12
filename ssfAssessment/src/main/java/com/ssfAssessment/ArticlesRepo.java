package com.ssfAssessment;

public interface ArticlesRepo {
    public int saveArticles(final Articles atc);
    public Articles findById(final String aId);
    public int update(final Articles atc);
}
