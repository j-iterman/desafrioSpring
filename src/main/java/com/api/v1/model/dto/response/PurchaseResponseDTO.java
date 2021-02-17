package com.api.v1.model.dto.response;

import com.api.v1.model.dto.request.ArticleDTO;

import java.util.ArrayList;

public class PurchaseResponseDTO {
    private static int instanceCounter = 0; //TODO: verificar por que se incrementa mas de lo esperado
    private final Integer id;
    private double totalCost;
    private ArrayList<ArticleDTO> articles;

    public PurchaseResponseDTO() {
        instanceCounter++;
        this.id = instanceCounter;
        this.articles = new ArrayList<>();
        this.totalCost = 0;
    }

    public Integer getId() {
        return id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public ArrayList<ArticleDTO> getArticles() {
        return articles;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setArticles(ArrayList<ArticleDTO> articles) {
        this.articles = articles;
    }
}
