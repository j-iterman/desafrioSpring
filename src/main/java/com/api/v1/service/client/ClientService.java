package com.api.v1.service.client;

import com.api.v1.model.dto.request.ClientDTO;

import java.io.IOException;
import java.util.ArrayList;

public interface ClientService {
    void createClient(ClientDTO clientDTO) throws IOException;

    ArrayList<ClientDTO> getClients() throws IOException;

    ArrayList<ClientDTO> getClientsByProvince(String province) throws IOException;
}
