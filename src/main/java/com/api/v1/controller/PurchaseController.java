package com.api.v1.controller;

import com.api.v1.model.dto.request.PurchaseDTO;
import com.api.v1.model.dto.response.PurchaseResponseDTO;
import com.api.v1.service.purchase.PurchaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/purchase-request")
public class PurchaseController {

    private final PurchaseService purchaseService;

    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<PurchaseResponseDTO> getTotalCost(@RequestBody PurchaseDTO purchaseDTO){
        try {
            return ResponseEntity.ok(purchaseService.addArticlesToShoppingCart(purchaseDTO));
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Failed to load database", e);
        }
    }
}
