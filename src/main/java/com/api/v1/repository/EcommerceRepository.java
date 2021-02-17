package com.api.v1.repository;

import com.api.v1.model.dto.request.ArticleDTO;
import com.api.v1.model.dto.request.ClientDTO;
import com.api.v1.model.dto.response.PurchaseResponseDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public interface EcommerceRepository {
    ArrayList<ArticleDTO> getAllArticles() throws IOException;

    String getDefaultOrderParam() throws IOException;

    ArticleDTO getArticleById(Integer id) throws IOException;

    HashMap<String, PurchaseResponseDTO> getShoppingCarts() throws IOException;

    void updateShoppingCarts(HashMap<String, PurchaseResponseDTO> shoppingCarts) throws IOException;

    void createClient(ClientDTO clientDTO) throws IOException;

    ArrayList<ClientDTO> loadClientsDataBase() throws IOException;

    ArrayList<ClientDTO> getClientsByProvince(String province) throws IOException;
}
