package com.example.newshouse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONRespond {

    @SerializedName("articles")
    @Expose
    private Articles[] articles;

    public Articles[] getArticles() {
        return articles;
    }

    public void setArticles(Articles[] articles) {
        this.articles = articles;
    }


}
