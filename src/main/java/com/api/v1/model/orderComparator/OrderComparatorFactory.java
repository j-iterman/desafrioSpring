package com.api.v1.model.orderComparator;

import com.api.v1.exceptions.NoSuchArticleException;
import com.api.v1.exceptions.NoSuchSortParamException;
import com.api.v1.model.dto.request.ArticleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;

public class OrderComparatorFactory {

    public static Comparator<ArticleDTO> getComparator(String sortParam){
        Comparator<ArticleDTO> comparator = null;

        switch (sortParam) {
            case "0":
                comparator = Comparator.comparing(ArticleDTO::getName);
                break;
            case "1":
                comparator = Comparator.comparing(ArticleDTO::getName).reversed();
                break;
            case "2":
                comparator = (a, b) -> (int) (a.getPrice() - b.getPrice());
                break;
            case "3":
                comparator = (a, b) -> (int) (b.getPrice() - a.getPrice());
                break;

            default:
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Invalid sorting param", new NoSuchSortParamException());
        }

        return comparator;
    }
}
