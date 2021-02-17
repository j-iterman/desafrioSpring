package com.api.v1.model.dto.response;

import com.api.v1.model.dto.request.ArticleDTO;

import java.util.ArrayList;

public class ArticleResponseDTO {

    private ArrayList<ArticleDTO> articles;

    public ArrayList<ArticleDTO> getArticles() {
        return articles;
    }

    public void setArticles(ArrayList<ArticleDTO> articles) {
        this.articles = articles;
    }
}
