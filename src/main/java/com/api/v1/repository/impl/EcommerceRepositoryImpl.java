package com.api.v1.repository.impl;

import com.api.v1.model.dto.request.ArticleDTO;
import com.api.v1.model.dto.request.ClientDTO;
import com.api.v1.model.dto.response.PurchaseResponseDTO;
import com.api.v1.repository.EcommerceRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

@Repository
public class EcommerceRepositoryImpl implements EcommerceRepository {

    public ArrayList<ArticleDTO> getAllArticles() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(new File("src/main/resources/bdd.json"), new TypeReference<>() {});
    }

    public ArrayList<ClientDTO> loadClientsDataBase() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("src/main/resources/client.json"), new TypeReference<>() {});
    }

    @Override
    public HashMap<String, PurchaseResponseDTO> getShoppingCarts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File("src/main/resources/receipts.json"), new TypeReference<>() {});
    }

    @Override
    public void updateShoppingCarts(HashMap<String, PurchaseResponseDTO> shoppingCarts) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/main/resources/receipts.json"), shoppingCarts);
    }

    @Override
    public void createClient(ClientDTO clientDTO) throws IOException {
        ArrayList<ClientDTO> clients = loadClientsDataBase();

        for (ClientDTO client: clients){
            if (client.getUsername().equals(clientDTO.getUsername())){
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Username already exists");
            }
        }

        clients.add(clientDTO);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("src/main/resources/client.json"), clients);
    }

    @Override
    public ArticleDTO getArticleById(Integer id) throws IOException {

        ArticleDTO result = null;

        ArrayList<ArticleDTO> database = getAllArticles();

        for (ArticleDTO art: database) {
            if (art.getId().equals(id)) {
                result = art;
                break;
            }
        }

        return result;
    }

    public ArrayList<ClientDTO> getClientsByProvince(String province) throws IOException {
        ArrayList<ClientDTO> clients = loadClientsDataBase();

        ArrayList<ClientDTO> clientsForProvince = new ArrayList<>();

        for (ClientDTO client: clients){
            if (client.getProvince().equals(province)) clientsForProvince.add(client);
        }

        return clientsForProvince;
    }

    @Override
    public String getDefaultOrderParam() throws IOException {
        Properties p = new Properties();

        FileInputStream reader = new FileInputStream("src/main/resources/application.properties");
        p.load(reader);

        return p.getProperty("defaultOrder");
    }

}
