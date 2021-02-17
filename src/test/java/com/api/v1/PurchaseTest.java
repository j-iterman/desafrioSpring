package com.api.v1;

import com.api.v1.model.dto.request.ArticleDTO;
import com.api.v1.repository.EcommerceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseTest {

    @MockBean
    private EcommerceRepository ecommerceRepository;

    @Autowired
    private MockMvc mockMvc;

    private ArrayList<ArticleDTO> articles;

    @BeforeEach
    void setUp(){
        articles = new ArrayList<>();
    }

    @Test
    public void getTotalCostShouldReturnAccurateTotalCost() throws Exception {

        ArticleDTO article1 = new ArticleDTO(
                1,
                "Desmalezadora",
                "Herramientas",
                "Makita",
                9600,
                5,
                true,
                3);

        ArticleDTO article2 = new ArticleDTO(
                2,
                "Remera",
                "Ropa",
                "Falabella",
                1000,
                7,
                true,
                1);

        ArticleDTO article3 = new ArticleDTO(
                3,
                "Redmi Note 8 Pro",
                "Celulares",
                "Xiaomi",
                50000,
                19,
                false,
                4);

        ArticleDTO article4 = new ArticleDTO(
                4,
                "Auriculares inalambricos",
                "Auriculares",
                "Samsung",
                35000,
                9,
                true,
                5);

        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);

        HashMap<String, ArrayList<ArticleDTO>> response = new HashMap<>();
        response.put("articles", articles);

        String result = ApiApplicationTests.convertObjectAsString(response);

        when(ecommerceRepository.getAllArticles()).thenReturn(articles);
        when(ecommerceRepository.getDefaultOrderParam()).thenReturn("0");

        this.mockMvc.perform(get("/api/v1/purchase-request"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

}
