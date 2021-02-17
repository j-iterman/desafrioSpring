package com.api.v1.model.dto.request;

import java.util.ArrayList;

public class PurchaseDTO {
    private String username;
    private ArrayList<ArticlesPurchaseDTO> articles;

    public String getUsername() {
        return username;
    }

    public ArrayList<ArticlesPurchaseDTO> getArticles() {
        return articles;
    }
}
