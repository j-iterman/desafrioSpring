package com.api.v1.model.dto.request;

public class ArticleDTO {
    private Integer id;
    private String name;
    private String category;
    private String brand;
    private double price;
    private Integer quantity;
    private boolean freeShipping;
    private Integer prestige;

    public ArticleDTO(Integer id, String name, String category,
                      String brand, double price, Integer quantity,
                      boolean freeShipping, Integer prestige) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.quantity = quantity;
        this.freeShipping = freeShipping;
        this.prestige = prestige;
    }

    public ArticleDTO() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public double getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public boolean getFreeShipping() {
        return freeShipping;
    }

    public Integer getPrestige() {
        return prestige;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
