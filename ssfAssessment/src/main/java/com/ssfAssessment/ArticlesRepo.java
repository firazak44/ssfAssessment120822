package com.ssfAssessment;

public interface ArticlesRepo {
    public int save(final Articles atc);
    public Articles findById(final String aId);
    public int update(final Articles atc);
}
