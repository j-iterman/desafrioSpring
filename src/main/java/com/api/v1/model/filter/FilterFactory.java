package com.api.v1.model.filter;

import com.api.v1.model.dto.request.ArticleDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class FilterFactory {

    public static Predicate<ArticleDTO> getProductFilter(HashMap<String, String> params) {
        List<Predicate<ArticleDTO>> allPredicates;
        allPredicates = getAllPredicates(params);
        return allPredicates.stream().reduce(p -> true, Predicate::and);
    }

    private static List<Predicate<ArticleDTO>> getAllPredicates(HashMap<String, String> params) {
        List<Predicate<ArticleDTO>> allPredicates = new ArrayList<>();

        if (params.containsKey("name")) {
            allPredicates.add(ArticleUtils.nameFilter(params.get("name")));
        }
        if (params.containsKey("category")) {
            allPredicates.add(ArticleUtils.categoryFilter(params.get("category")));
        }
        if (params.containsKey("brand")) {
            allPredicates.add(ArticleUtils.brandFilter(params.get("brand")));
        }
        if (params.containsKey("freeShipping")) {
            allPredicates.add(ArticleUtils.freeShippingFilter(params.get("freeShipping")));
        }
        if (params.containsKey("minPrestige")) {
            allPredicates.add(ArticleUtils.minPrestigeFilter(params.get("minPrestige")));
        }
        if (params.containsKey("maxPrice")) {
            allPredicates.add(ArticleUtils.maxPriceFilter(params.get("maxPrice")));
        }

        return allPredicates;
    }
}
