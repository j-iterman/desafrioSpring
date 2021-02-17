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
public class ArticleTest {

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
    public void getArticlesShouldReturnAllArticles() throws Exception {

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

        this.mockMvc.perform(get("/api/v1/articles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

    @Test
    public void getArticlesShouldReturnArticlesWithCategoryHerramientas() throws Exception {

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
                "Martillo",
                "Herramientas",
                "Falabella",
                700,
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

        ArrayList<ArticleDTO> responseArr = new ArrayList<>();
        responseArr.add(article1);
        responseArr.add(article2);

        HashMap<String, ArrayList<ArticleDTO>> response = new HashMap<>();
        response.put("articles", responseArr);

        String result = ApiApplicationTests.convertObjectAsString(response);

        when(ecommerceRepository.getAllArticles()).thenReturn(articles);
        when(ecommerceRepository.getDefaultOrderParam()).thenReturn("0");

        this.mockMvc.perform(get("/api/v1/articles?category=herramientas"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

    @Test
    public void getArticlesShouldReturnArticlesWithCategoryHerramientasAndFreeShipping() throws Exception {

        ArticleDTO article1 = new ArticleDTO(
                1,
                "Colchon resortes",
                "Colchones",
                "Canon",
                45000,
                5,
                true,
                4);

        ArticleDTO article2 = new ArticleDTO(
                2,
                "Martillo",
                "Herramientas",
                "Falabella",
                700,
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

        ArticleDTO article5 = new ArticleDTO(
                5,
                "Pinza",
                "Herramientas",
                "Generica",
                600,
                3,
                false,
                5);

        ArticleDTO article6 = new ArticleDTO(
                6,
                "Desmalezadora",
                "Herramientas",
                "Makita",
                9600,
                5,
                true,
                3);

        ArticleDTO article7 = new ArticleDTO(
                7,
                "Camara Reflex",
                "Camaras",
                "Canon",
                250000,
                2,
                true,
                5);

        ArticleDTO article8 = new ArticleDTO(
                8,
                "Galletitas",
                "Merienda",
                "oreo",
                100,
                78,
                false,
                4);

        articles.add(article1);
        articles.add(article2);
        articles.add(article3);
        articles.add(article4);
        articles.add(article5);
        articles.add(article6);
        articles.add(article7);
        articles.add(article8);

        ArrayList<ArticleDTO> responseArr = new ArrayList<>();
        responseArr.add(article6);
        responseArr.add(article2);

        HashMap<String, ArrayList<ArticleDTO>> response = new HashMap<>();
        response.put("articles", responseArr);

        String result = ApiApplicationTests.convertObjectAsString(response);

        when(ecommerceRepository.getAllArticles()).thenReturn(articles);
        when(ecommerceRepository.getDefaultOrderParam()).thenReturn("0");

        this.mockMvc.perform(get("/api/v1/articles?category=herramientas&freeShipping=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

    @Test
    public void getArticlesShouldReturnArticlesInAscendingAlphabeticalOrder() throws Exception {

        ArticleDTO article1 = new ArticleDTO(
                1,
                "Colchon resortes",
                "Colchones",
                "Canon",
                45000,
                5,
                true,
                4);

        ArticleDTO article2 = new ArticleDTO(
                2,
                "Martillo",
                "Herramientas",
                "Falabella",
                700,
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

        ArrayList<ArticleDTO> responseArr = new ArrayList<>();
        responseArr.add(article4);
        responseArr.add(article1);
        responseArr.add(article2);
        responseArr.add(article3);

        HashMap<String, ArrayList<ArticleDTO>> response = new HashMap<>();
        response.put("articles", responseArr);

        String result = ApiApplicationTests.convertObjectAsString(response);

        when(ecommerceRepository.getAllArticles()).thenReturn(articles);
        when(ecommerceRepository.getDefaultOrderParam()).thenReturn("0");

        this.mockMvc.perform(get("/api/v1/articles?order=0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result, true));
    }

    @Test
    public void getArticlesShouldReturnArticlesInDescendingAlphabeticalOrder() throws Exception {

        ArticleDTO article1 = new ArticleDTO(
                1,
                "Colchon resortes",
                "Colchones",
                "Canon",
                45000,
                5,
                true,
                4);

        ArticleDTO article2 = new ArticleDTO(
                2,
                "Martillo",
                "Herramientas",
                "Falabella",
                700,
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

        ArrayList<ArticleDTO> responseArr = new ArrayList<>();
        responseArr.add(article3);
        responseArr.add(article2);
        responseArr.add(article1);
        responseArr.add(article4);

        HashMap<String, ArrayList<ArticleDTO>> response = new HashMap<>();
        response.put("articles", responseArr);

        String result = ApiApplicationTests.convertObjectAsString(response);

        when(ecommerceRepository.getAllArticles()).thenReturn(articles);
        when(ecommerceRepository.getDefaultOrderParam()).thenReturn("0");

        this.mockMvc.perform(get("/api/v1/articles?order=1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result, true));
    }

    @Test
    public void getArticlesShouldReturnArticlesInAscendingPriceOrder() throws Exception {

        ArticleDTO article1 = new ArticleDTO(
                1,
                "Colchon resortes",
                "Colchones",
                "Canon",
                45000,
                5,
                true,
                4);

        ArticleDTO article2 = new ArticleDTO(
                2,
                "Martillo",
                "Herramientas",
                "Falabella",
                700,
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

        ArrayList<ArticleDTO> responseArr = new ArrayList<>();
        responseArr.add(article2);
        responseArr.add(article4);
        responseArr.add(article1);
        responseArr.add(article3);

        HashMap<String, ArrayList<ArticleDTO>> response = new HashMap<>();
        response.put("articles", responseArr);

        String result = ApiApplicationTests.convertObjectAsString(response);

        when(ecommerceRepository.getAllArticles()).thenReturn(articles);
        when(ecommerceRepository.getDefaultOrderParam()).thenReturn("0");

        this.mockMvc.perform(get("/api/v1/articles?order=2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result, true));
    }

    @Test
    public void getArticlesShouldReturnArticlesInDescendingPriceOrder() throws Exception {

        ArticleDTO article1 = new ArticleDTO(
                1,
                "Colchon resortes",
                "Colchones",
                "Canon",
                45000,
                5,
                true,
                4);

        ArticleDTO article2 = new ArticleDTO(
                2,
                "Martillo",
                "Herramientas",
                "Falabella",
                700,
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

        ArrayList<ArticleDTO> responseArr = new ArrayList<>();
        responseArr.add(article3);
        responseArr.add(article1);
        responseArr.add(article4);
        responseArr.add(article2);

        HashMap<String, ArrayList<ArticleDTO>> response = new HashMap<>();
        response.put("articles", responseArr);

        String result = ApiApplicationTests.convertObjectAsString(response);

        when(ecommerceRepository.getAllArticles()).thenReturn(articles);
        when(ecommerceRepository.getDefaultOrderParam()).thenReturn("0");

        this.mockMvc.perform(get("/api/v1/articles?order=3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(result, true));
    }

}
