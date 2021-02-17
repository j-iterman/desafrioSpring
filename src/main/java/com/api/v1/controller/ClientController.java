package com.api.v1.controller;

import com.api.v1.model.dto.request.ClientDTO;
import com.api.v1.service.client.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/newclient")
    @ResponseBody
    public ResponseEntity.BodyBuilder createClient(@RequestBody ClientDTO clientDTO){
        try {
            clientService.createClient(clientDTO);
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load clients database", e);
        }
        return ResponseEntity.ok();
    }

    @GetMapping("")
    @ResponseBody
    public ResponseEntity<ArrayList<ClientDTO>> getClients(){
        try {
            return ResponseEntity.ok(clientService.getClients());
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load clients database", e);
        }
    }

    @GetMapping("{province}")
    @ResponseBody
    public ResponseEntity<ArrayList<ClientDTO>> getClientsByProvince(@PathVariable String province){
        try {
            return ResponseEntity.ok(clientService.getClientsByProvince(province));
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load clients database", e);
        }
    }
}
