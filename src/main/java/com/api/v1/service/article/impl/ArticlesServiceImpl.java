package com.api.v1.service.article.impl;

import com.api.v1.model.dto.request.ArticleDTO;
import com.api.v1.model.dto.response.ArticleResponseDTO;
import com.api.v1.model.filter.FilterFactory;
import com.api.v1.model.orderComparator.OrderComparatorFactory;
import com.api.v1.utils.sorter.Sorter;
import com.api.v1.utils.sorter.SorterFactory;
import com.api.v1.repository.EcommerceRepository;
import com.api.v1.service.article.ArticlesService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ArticlesServiceImpl implements ArticlesService {

    private final EcommerceRepository ecommerceRepository;

    public ArticlesServiceImpl(EcommerceRepository ecommerceRepository) {
        this.ecommerceRepository = ecommerceRepository;
    }

    @Override
    public ArticleResponseDTO getArticles(HashMap<String, String> params) throws IOException {
        ArticleResponseDTO response = new ArticleResponseDTO();
        ArrayList<ArticleDTO> articles = null;

        articles = ecommerceRepository.getAllArticles();

        Predicate<ArticleDTO> compositeFilters = FilterFactory.getProductFilter(params);

        articles = articles.stream()
                .filter(compositeFilters)
                .collect(Collectors.toCollection(ArrayList::new));

        this.setDefaultOrder(params);
        response.setArticles(this.sortArticles(articles, params.get("order")));

        return response;
    }

    public ArrayList<ArticleDTO> sortArticles(ArrayList<ArticleDTO> articles, String sortParam){

        Sorter sorter = SorterFactory.getInstance();

        Comparator<ArticleDTO> c = OrderComparatorFactory.getComparator(sortParam);
        sorter.sort(articles, c);

        return articles;
    }

    public void setDefaultOrder(HashMap<String, String> params) throws IOException {
        String defaultParam = ecommerceRepository.getDefaultOrderParam();
        params.put("order", params.getOrDefault("order", defaultParam));
    }
}
