package com.ssfAssessment.app;

public interface ArticlesRepo {
    public int saveArticles(final Articles atc);
    public Articles findById(final String aId);
    public int updatessfAssessment(final Articles atc);
}
