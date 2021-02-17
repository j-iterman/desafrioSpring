package com.api.v1.service.purchase;

import com.api.v1.model.dto.request.PurchaseDTO;
import com.api.v1.model.dto.response.PurchaseResponseDTO;

import java.io.IOException;

public interface PurchaseService {
    PurchaseResponseDTO addArticlesToShoppingCart(PurchaseDTO purchaseDTO) throws IOException;
}
