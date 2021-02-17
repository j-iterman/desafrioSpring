package com.api.v1.service.purchase.impl;

import com.api.v1.exceptions.NoSuchArticleException;
import com.api.v1.exceptions.NoSuchQuantityException;
import com.api.v1.model.dto.request.ArticleDTO;
import com.api.v1.model.dto.request.ArticlesPurchaseDTO;
import com.api.v1.model.dto.request.PurchaseDTO;
import com.api.v1.model.dto.response.PurchaseResponseDTO;
import com.api.v1.repository.EcommerceRepository;
import com.api.v1.service.purchase.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private final EcommerceRepository ecommerceRepository;

    public PurchaseServiceImpl(EcommerceRepository ecommerceRepository) {
        this.ecommerceRepository = ecommerceRepository;
    }

    @Override
    public PurchaseResponseDTO addArticlesToShoppingCart(PurchaseDTO purchaseDTO) throws IOException {

        HashMap<String, PurchaseResponseDTO> shoppingCarts = ecommerceRepository.getShoppingCarts();
        PurchaseResponseDTO clientCart = shoppingCarts.get(purchaseDTO.getUsername());
        if (clientCart == null) clientCart = new PurchaseResponseDTO();

        ArrayList<ArticleDTO> articles = clientCart.getArticles();
        double totalCost = clientCart.getTotalCost();

        for (ArticlesPurchaseDTO art: purchaseDTO.getArticles()){
            ArticleDTO article = ecommerceRepository.getArticleById(art.getProductId());

            this.validatePurchase(article, art);

            articles.add(article);
            totalCost += article.getPrice() * art.getQuantity();

            article.setQuantity(article.getQuantity()-art.getQuantity());
        }

        clientCart.setArticles(removeDuplicates(articles));
        clientCart.setTotalCost(totalCost);

        shoppingCarts.put(purchaseDTO.getUsername(), clientCart);
        ecommerceRepository.updateShoppingCarts(shoppingCarts);

        return clientCart;
    }

    private void validatePurchase(ArticleDTO article, ArticlesPurchaseDTO art) {
        if (article==null) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "At least one of the articles does not exist", new NoSuchArticleException());

        if (art.getQuantity() < 1 || art.getQuantity() > article.getQuantity()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid article quantity", new NoSuchQuantityException());
        }
    }

    private ArrayList<ArticleDTO> removeDuplicates(ArrayList<ArticleDTO> articles){

        TreeSet<ArticleDTO> withoutDuplicates = articles.stream()
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(Comparator.comparing(ArticleDTO::getId))
                ));

        return new ArrayList<>(withoutDuplicates);

    }
}
