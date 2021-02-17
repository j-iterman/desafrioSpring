package com.api.v1.model.filter;

import com.api.v1.model.dto.request.ArticleDTO;

import java.util.function.Predicate;

public class ArticleUtils {

    public static Predicate<ArticleDTO> categoryFilter(String category) {
        return (ArticleDTO a) -> a.getCategory().equalsIgnoreCase(category);
    }

    public static Predicate<ArticleDTO> brandFilter(String brand) {
        return (ArticleDTO a) -> a.getBrand().equalsIgnoreCase(brand);
    }

    public static Predicate<ArticleDTO> freeShippingFilter(String freeShipping) {
        return (ArticleDTO a) -> (a.getFreeShipping() == Boolean.parseBoolean(freeShipping));
    }

    public static Predicate<ArticleDTO> nameFilter(String name) {
        return (ArticleDTO a) -> a.getName().contains(name);
    }

    public static Predicate<ArticleDTO> maxPriceFilter(String maxPrice) {
        return (ArticleDTO a) -> ((a.getPrice() <= Integer.parseInt(maxPrice)));
    }

    public static Predicate<ArticleDTO> minPrestigeFilter(String prestige) {
        return (ArticleDTO a) -> ((a.getPrestige() >= Integer.parseInt(prestige)));
    }
}
