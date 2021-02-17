package com.api.v1.service.client.impl;

import com.api.v1.model.dto.request.ClientDTO;
import com.api.v1.repository.EcommerceRepository;
import com.api.v1.service.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class ClientServiceImpl implements ClientService {
    private final EcommerceRepository ecommerceRepository;

    public ClientServiceImpl(EcommerceRepository ecommerceRepository) {
        this.ecommerceRepository = ecommerceRepository;
    }

    @Override
    public void createClient(ClientDTO clientDTO) throws IOException {

        if (clientDTO.getUsername() == null ||
                clientDTO.getNombre() == null ||
                clientDTO.getEdad() == null ||
                clientDTO.getProvince() == null){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Missing information field");
        }

        ecommerceRepository.createClient(clientDTO);

    }

    @Override
    public ArrayList<ClientDTO> getClients() throws IOException {
        return ecommerceRepository.loadClientsDataBase();
    }

    @Override
    public ArrayList<ClientDTO> getClientsByProvince(String province) throws IOException {
        return ecommerceRepository.getClientsByProvince(province);
    }
}
